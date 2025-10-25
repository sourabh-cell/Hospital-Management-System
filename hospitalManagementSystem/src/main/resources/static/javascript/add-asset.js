// Utility Functions
class AssetUtils {
    static showNotification(message, type = 'success') {
        document.querySelectorAll('.asset-notification').forEach(a => a.remove());

        const notification = document.createElement('div');
        notification.className = `asset-notification alert alert-${type} alert-dismissible fade show`;
        notification.innerHTML = `
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;

        document.querySelector('.content-wrapper')?.prepend(notification);
        setTimeout(() => notification.remove(), 5000);
    }

    static debounce(func, wait) {
        let timeout;
        return function (...args) {
            clearTimeout(timeout);
            timeout = setTimeout(() => func(...args), wait);
        };
    }
}

// Form Validation Class
class AssetFormValidator {
    constructor() {
        this.errors = new Map();
    }

    clearErrors() {
        this.errors.clear();
        document.querySelectorAll('.error-text').forEach(el => {
            el.textContent = '';
            el.style.display = 'none';
        });
        document.querySelectorAll('.is-invalid').forEach(el => el.classList.remove('is-invalid'));
    }

    showError(fieldId, message) {
        const errorEl = document.getElementById(`${fieldId}Error`);
        const inputEl = document.getElementById(fieldId);
        if (errorEl && inputEl) {
            errorEl.textContent = message;
            errorEl.style.display = 'block';
            inputEl.classList.add('is-invalid');
            this.errors.set(fieldId, message);
        }
    }

    validateRequired(value, fieldId, fieldName) {
        if (!value.trim()) {
            this.showError(fieldId, `${fieldName} is required`);
            return false;
        }
        return true;
    }

    validateLength(value, fieldId, fieldName, min = 1, max = 255) {
        if (value.length < min) {
            this.showError(fieldId, `${fieldName} must be at least ${min} characters`);
            return false;
        }
        if (value.length > max) {
            this.showError(fieldId, `${fieldName} must be less than ${max} characters`);
            return false;
        }
        return true;
    }

    validateDate(value, fieldId, fieldName, allowFuture = false) {
        if (!value) {
            this.showError(fieldId, `${fieldName} is required`);
            return false;
        }

        const date = new Date(value);
        const today = new Date();
        if (isNaN(date)) {
            this.showError(fieldId, `${fieldName} is invalid`);
            return false;
        }
        if (!allowFuture && date > today) {
            this.showError(fieldId, `${fieldName} cannot be in the future`);
            return false;
        }
        return true;
    }

    validateWarrantyDate(warrantyDate, purchaseDate, fieldId) {
        if (!warrantyDate) return true;
        const warranty = new Date(warrantyDate);
        const purchase = new Date(purchaseDate);
        if (warranty <= purchase) {
            this.showError(fieldId, 'Warranty date must be after purchase date');
            return false;
        }
        return true;
    }

    validateForm(formData) {
        this.clearErrors();

        const validations = [
            () => this.validateRequired(formData.assetId, 'uniqueAssetId', 'Asset ID'),
            () => this.validateLength(formData.assetId, 'uniqueAssetId', 'Asset ID', 2, 100),

            () => this.validateRequired(formData.serialNumber, 'uniqueSerialNumber', 'Serial Number'),
            () => this.validateLength(formData.serialNumber, 'uniqueSerialNumber', 'Serial Number', 2, 200),

            () => this.validateRequired(formData.model, 'uniqueModel', 'Model'),
            () => this.validateLength(formData.model, 'uniqueModel', 'Model', 2, 200),

            () => this.validateRequired(formData.vendor, 'uniqueVendor', 'Vendor'),
            () => this.validateLength(formData.vendor, 'uniqueVendor', 'Vendor', 2, 200),

            () => this.validateDate(formData.purchaseDate, 'uniquePurchaseDate', 'Purchase Date', false),

            () => this.validateRequired(formData.departmentBranch, 'uniqueDepartment', 'Department/Branch'),

            () => this.validateRequired(formData.amcCmcDetails, 'uniqueAmcDetails', 'AMC/CMC Details'),
            () => this.validateLength(formData.amcCmcDetails, 'uniqueAmcDetails', 'AMC/CMC Details', 2, 500),

            () => !formData.warrantyDate || this.validateDate(formData.warrantyDate, 'uniqueWarranty', 'Warranty Date', true),
            () => !formData.warrantyDate || this.validateWarrantyDate(formData.warrantyDate, formData.purchaseDate, 'uniqueWarranty')
        ];

        return validations.every(v => v());
    }
}

// Form Handler (without API)
class AssetFormHandler {
    constructor() {
        this.validator = new AssetFormValidator();
        this.form = document.getElementById('uniqueAssetForm');
        if (!this.form) return;

        this.initializeListeners();
        this.initializeDatePickers();
    }

    initializeListeners() {
        this.form.addEventListener('submit', e => this.handleSubmit(e));

        this.form.querySelectorAll('input, select, textarea').forEach(input => {
            input.addEventListener('blur', () => this.validateField(input));
            input.addEventListener('input', AssetUtils.debounce(() => this.validateField(input), 400));
        });

        this.form.addEventListener('reset', () => {
            this.validator.clearErrors();
            setTimeout(() => this.initializeDatePickers(), 0);
        });
    }

    initializeDatePickers() {
        const warrantyInput = document.getElementById('uniqueWarranty');
        const purchaseInput = document.getElementById('uniquePurchaseDate');

        if (purchaseInput && typeof flatpickr !== 'undefined') {
            this.purchasePicker = flatpickr("#uniquePurchaseDate", {
                dateFormat: "Y-m-d",
                maxDate: "today",
                allowInput: true,
                onChange: (dates) => {
                    if (dates[0] && this.warrantyPicker) {
                        const minWarranty = new Date(dates[0]);
                        minWarranty.setDate(minWarranty.getDate() + 1);
                        this.warrantyPicker.set('minDate', minWarranty);
                    }
                }
            });
        }

        if (warrantyInput && typeof flatpickr !== 'undefined') {
            this.warrantyPicker = flatpickr("#uniqueWarranty", {
                dateFormat: "Y-m-d",
                allowInput: true,
                minDate: new Date().fp_incr(1)
            });
        }
    }

    validateField(field) {
        const id = field.id, val = field.value.trim();
        this.validator.clearErrors();

        switch (id) {
            case 'uniqueAssetId':
                this.validator.validateRequired(val, id, 'Asset ID');
                this.validator.validateLength(val, id, 'Asset ID', 2, 100);
                break;
            case 'uniqueSerialNumber':
                this.validator.validateRequired(val, id, 'Serial Number');
                this.validator.validateLength(val, id, 'Serial Number', 2, 200);
                break;
            case 'uniqueModel':
                this.validator.validateRequired(val, id, 'Model');
                this.validator.validateLength(val, id, 'Model', 2, 200);
                break;
            case 'uniqueVendor':
                this.validator.validateRequired(val, id, 'Vendor');
                this.validator.validateLength(val, id, 'Vendor', 2, 200);
                break;
            case 'uniquePurchaseDate':
                this.validator.validateDate(val, id, 'Purchase Date', false);
                break;
            case 'uniqueWarranty':
                if (val) {
                    this.validator.validateDate(val, id, 'Warranty Date', true);
                    const purchaseDate = document.getElementById('uniquePurchaseDate')?.value;
                    if (purchaseDate) this.validator.validateWarrantyDate(val, purchaseDate, id);
                }
                break;
            case 'uniqueDepartment':
                this.validator.validateRequired(val, id, 'Department/Branch');
                break;
            case 'uniqueAmcDetails':
                this.validator.validateRequired(val, id, 'AMC/CMC Details');
                this.validator.validateLength(val, id, 'AMC/CMC Details', 2, 500);
                break;
        }
    }

   handleSubmit(e) {
       e.preventDefault();
       const data = this.getFormData();

       if (!this.validator.validateForm(data)) {
           AssetUtils.showNotification('Please fix the validation errors.', 'danger');
           return;
       }
       this.form.submit();
       AssetUtils.showNotification('Validation passed! Submitting...', 'success');
   }

    getFormData() {
        return {
            assetId: document.getElementById('uniqueAssetId')?.value || '',
            serialNumber: document.getElementById('uniqueSerialNumber')?.value || '',
            model: document.getElementById('uniqueModel')?.value || '',
            vendor: document.getElementById('uniqueVendor')?.value || '',
            purchaseDate: document.getElementById('uniquePurchaseDate')?.value || '',
            warrantyDate: document.getElementById('uniqueWarranty')?.value || '',
            departmentBranch: document.getElementById('uniqueDepartment')?.value || '',
            amcCmcDetails: document.getElementById('uniqueAmcDetails')?.value || '',
            remarksNotes: document.getElementById('uniqueRemarks')?.value || ''
        };
    }
}

// Initialize safely
document.addEventListener('DOMContentLoaded', () => {
    const styles = `
        .is-invalid { border-color: #dc3545 !important; box-shadow: 0 0 0 0.2rem rgba(220,53,69,0.25)!important; }
        .error-text { color: #dc3545; font-size: 0.875em; margin-top: 0.25rem; display: none; }
        .asset-notification { position: fixed; top: 100px; right: 20px; z-index: 9999; min-width: 300px; }
    `;
    const styleEl = document.createElement('style');
    styleEl.textContent = styles;
    document.head.appendChild(styleEl);

    const form = document.getElementById('uniqueAssetForm');
    if (form) new AssetFormHandler();
});
