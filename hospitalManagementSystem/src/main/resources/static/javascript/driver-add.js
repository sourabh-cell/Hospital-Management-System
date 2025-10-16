 
document.getElementById('addDriverForm').addEventListener('submit', function(e) {
  let valid = true;

  // Remove previous error messages
  document.querySelectorAll('.error-message').forEach(el => el.remove());

  // Driver Name validation (pattern already in HTML, but double checking)
  const driverNameInput = document.getElementById('driverName');
  if (!driverNameInput.value.match(/^[A-Za-z\s]+$/)) {
    showError(driverNameInput, "Only alphabets allowed");
    valid = false;
  }

  // License Number validation (Required, must not be empty)
  const licenseInput = document.getElementById('licenseNumber');
  if (!licenseInput.value.trim()) {
    showError(licenseInput, "License number is required");
    valid = false;
  }

  // Contact Number validation (pattern already in HTML, must be 10 digits)
  const contactInput = document.getElementById('contactNumber');
  if (!contactInput.value.match(/^\d{10}$/)) {
    showError(contactInput, "Enter a valid 10-digit number");
    valid = false;
  }

  // Ambulance selection validation
  const ambulanceSelect = document.getElementById('assignedAmbulance');
  if (!ambulanceSelect.value) {
    showError(ambulanceSelect, "Please select an ambulance");
    valid = false;
  }

  if (!valid) e.preventDefault();
});

// Helper function to show error messages
function showError(input, message) {
  const error = document.createElement('div');
  error.className = 'error-message';
  error.style.color = 'red';
  error.style.fontSize = '0.85em';
  error.textContent = message;
  input.parentNode.appendChild(error);
}
 
