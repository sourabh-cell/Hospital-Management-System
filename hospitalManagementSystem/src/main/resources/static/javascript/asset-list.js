// Config
const ASSET_CONFIG = { API_BASE_URL: 'http://localhost:8080/api/v1/assets', PAGE_SIZE: 10 };

// State
let assetState = { currentPage: 0, totalPages: 0, totalElements: 0, allAssets: [] };

// Utility
const AssetUtils = {
    debounce(func, wait) {
        let timeout;
        return (...args) => {
            clearTimeout(timeout);
            timeout = setTimeout(() => func(...args), wait);
        };
    },
    escapeHtml(text) {
        if (!text) return '';
        return text.toString()
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#039;");
    },
    showNotification(message, type = 'success') {
        alert(`${type.toUpperCase()}: ${message}`);
    }
};

// API
const AssetAPI = {
    async request(endpoint, options = {}) {
        const res = await fetch(`${ASSET_CONFIG.API_BASE_URL}${endpoint}`, {
            headers: { 'Content-Type': 'application/json' },
            ...options,
            body: options.body ? JSON.stringify(options.body) : undefined
        });
        if (!res.ok) throw new Error('API Error');
        return res.json();
    },
    getAllAssets(page = 0, size = ASSET_CONFIG.PAGE_SIZE) {
        return this.request(`?page=${page}&size=${size}&sortBy=createdAt&sortDirection=desc`);
    },
    deleteAsset(id) {
        return this.request(`/${id}`, { method: 'DELETE' });
    }
};

// Asset Manager
class AssetManager {
    async init() {
        this.initEvents();
        await this.loadAssets();
    }

    initEvents() {
        document.getElementById('refreshBtn')?.addEventListener('click', () => this.loadAssets());
        const searchInput = document.getElementById('searchInput');
        searchInput?.addEventListener('input', AssetUtils.debounce(() => this.applyFilters(), 500));

        document.getElementById('pagination')?.addEventListener('click', e => {
            if (e.target.classList.contains('page-link')) {
                e.preventDefault();
                const page = parseInt(e.target.dataset.page);
                if (!isNaN(page)) this.goToPage(page);
            }
        });
    }

    async loadAssets(page = 0) {
        try {
            const response = await AssetAPI.getAllAssets(page);
            if (!response || !response.success) throw new Error('Failed to load');

            assetState.allAssets = response.data.content || [];
            assetState.totalPages = response.data.totalPages || 0;
            assetState.totalElements = response.data.totalElements || 0;
            assetState.currentPage = page;

            this.renderTable(assetState.allAssets);
            this.renderPagination();
        } catch (e) {
            console.error(e);
            AssetUtils.showNotification('Failed to load assets', 'danger');
        }
    }

    renderTable(assets) {
        const tbody = document.getElementById('assetTableBody');
        if (!tbody) return;

        if (!assets.length) {
            tbody.innerHTML = `<tr><td colspan="10" class="text-center">No assets found</td></tr>`;
            return;
        }

        const startIndex = assetState.currentPage * ASSET_CONFIG.PAGE_SIZE;
        tbody.innerHTML = assets.map((asset, i) => `
            <tr>
                <td>${startIndex + i + 1}</td>
                <td>${AssetUtils.escapeHtml(asset.assetId)}</td>
                <td>${AssetUtils.escapeHtml(asset.serialNumber)}</td>
                <td>${AssetUtils.escapeHtml(asset.model)}</td>
                <td>${AssetUtils.escapeHtml(asset.vendor)}</td>
                <td>${AssetUtils.escapeHtml(asset.departmentBranch)}</td>
                <td>${asset.purchaseDate || ''}</td>
                <td>${asset.warrantyDate || ''}</td>
                <td>${asset.status}</td>
                <td>
                    <button class="btn btn-sm btn-warning edit-btn" data-id="${asset.id}">Edit</button>
                    <button class="btn btn-sm btn-danger delete-btn" data-id="${asset.id}">Delete</button>
                </td>
            </tr>
        `).join('');

        // Attach delete events
        document.querySelectorAll('.delete-btn').forEach(btn => {
            btn.addEventListener('click', async () => {
                if (!confirm('Delete this asset?')) return;
                try {
                    await AssetAPI.deleteAsset(btn.dataset.id);
                    AssetUtils.showNotification('Deleted successfully', 'success');
                    this.loadAssets(assetState.currentPage);
                } catch {
                    AssetUtils.showNotification('Delete failed', 'danger');
                }
            });
        });
    }

    applyFilters() {
        const search = document.getElementById('searchInput')?.value.toLowerCase() || '';
        const filtered = assetState.allAssets.filter(a =>
            a.assetId.toLowerCase().includes(search) ||
            a.model.toLowerCase().includes(search) ||
            a.vendor.toLowerCase().includes(search)
        );
        this.renderTable(filtered);
    }

    renderPagination() {
        const pagination = document.getElementById('pagination');
        if (!pagination) return;

        let html = '';
        for (let i = 0; i < assetState.totalPages; i++) {
            html += `<li class="page-item ${i === assetState.currentPage ? 'active' : ''}">
                        <a class="page-link" href="#" data-page="${i}">${i + 1}</a>
                     </li>`;
        }
        pagination.innerHTML = html;
    }

    goToPage(page) {
        if (page >= 0 && page < assetState.totalPages) this.loadAssets(page);
    }
}

// Init
document.addEventListener('DOMContentLoaded', () => new AssetManager().init());
