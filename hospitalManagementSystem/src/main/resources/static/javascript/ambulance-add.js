//document.addEventListener("DOMContentLoaded", () => {
//  const form = document.getElementById("addAmbulanceForm");
//  if (form) {
//    form.addEventListener("submit", function(e) {
//      console.log("Ambulance form submitted!"); // From your first script
//
//      let valid = true;
//
//      // Clear old error messages
//      document.querySelectorAll('.error-message').forEach(el => el.remove());
//
//      // Vehicle Number validation
//      const vehicleInput = document.getElementById('vehicleNumber');
//      const vehiclePattern = /^[A-Z]{2}\d{2}-\d{4}$/;
//      if (!vehiclePattern.test(vehicleInput.value.trim())) {
//        showError(vehicleInput, "Format: MH12-1234");
//        valid = false;
//      }
//
//      // Type validation
//      const typeSelect = document.getElementById('type');
//      if (!typeSelect.value || typeSelect.value === "Select Type") {
//        showError(typeSelect, "Select a type");
//        valid = false;
//      }
//
//      // Status validation
//      const statusSelect = document.getElementById('status');
//      if (!statusSelect.value || statusSelect.value === "Select Status") {
//        showError(statusSelect, "Select a status");
//        valid = false;
//      }
//
//      // Maintenance Date validation
//      const dateInput = document.getElementById('maintenanceDate');
//      if (!dateInput.value) {
//        showError(dateInput, "Date required");
//        valid = false;
//      }
//
//      if (!valid) e.preventDefault();
//    });
//  }
//
//  // Helper function for errors
//  function showError(input, message) {
//    const error = document.createElement('div');
//    error.className = 'error-message';
//    error.style.color = 'red';
//    error.style.fontSize = '0.85em';
//    error.textContent = message;
//    input.parentNode.appendChild(error);
//  }
//});
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//---------------------------------------------------------------------------------
//document.addEventListener("DOMContentLoaded", () => {
//  const form = document.getElementById("ambulanceForm");
//  if (form) {
//    form.addEventListener("submit", (e) => {
//      e.preventDefault();
//      console.log("Ambulance form submitted!");
//    });
//  }
//});
//
//
//
//
//
//
//
//
//
//
document.getElementById('addAmbulanceForm').addEventListener('submit', function(e) {
  let valid = true;

  // Clear old error messages
  document.querySelectorAll('.error-message').forEach(el => el.remove());

  // Vehicle Number validation
  const vehicleInput = document.getElementById('vehicleNumber');
  const vehiclePattern = /^[A-Z]{2}\d{2}-\d{4}$/;
  if (!vehiclePattern.test(vehicleInput.value.trim())) {
    showError(vehicleInput, "Format: MH12-1234");
    valid = false;
  }

  // Type validation
  const typeSelect = document.getElementById('type');
  if (!typeSelect.value || typeSelect.value === "Select Type") {
    showError(typeSelect, "Select a type");
    valid = false;
  }

  // Status validation
  const statusSelect = document.getElementById('status');
  if (!statusSelect.value || statusSelect.value === "Select Status") {
    showError(statusSelect, "Select a status");
    valid = false;
  }

  // Maintenance Date validation
  const dateInput = document.getElementById('maintenanceDate');
  if (!dateInput.value) {
    showError(dateInput, "Date required");
    valid = false;
  }

  if (!valid) e.preventDefault();
});

// Helper function for errors
function showError(input, message) {
  const error = document.createElement('div');
  error.className = 'error-message';
  error.style.color = 'red';
  error.style.fontSize = '0.85em';
  error.textContent = message;
  input.parentNode.appendChild(error);
}

