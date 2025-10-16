 
document.getElementById('ambulanceAssignmentForm').addEventListener('submit', function(e) {
  let valid = true;

  // Remove old error messages
  document.querySelectorAll('.error-message').forEach(el => el.remove());

  // Validate ambulance select
  const ambulanceSelect = document.getElementById('assignAmbulance');
  if (!ambulanceSelect.value || ambulanceSelect.value === "Select Ambulance") {
    showError(ambulanceSelect, "Please select an ambulance");
    valid = false;
  }

  // Validate driver select
  const driverSelect = document.getElementById('assignDriver');
  if (!driverSelect.value || driverSelect.value === "Select Driver") {
    showError(driverSelect, "Please select a driver");
    valid = false;
  }

  // Validate patient select
  const patientSelect = document.getElementById('assignPatient');
  if (!patientSelect.value || patientSelect.value === "Select Patient") {
    showError(patientSelect, "Please select a patient");
    valid = false;
  }

  // Validate pickup location
  const fromLocation = document.getElementById('fromLocation');
  if (!fromLocation.value.trim()) {
    showError(fromLocation, "Enter pickup location");
    valid = false;
  }

  // Validate drop location
  const toLocation = document.getElementById('toLocation');
  if (!toLocation.value.trim()) {
    showError(toLocation, "Enter drop location");
    valid = false;
  }

  // Validate start time
  const startTime = document.getElementById('startTime');
  if (!startTime.value) {
    showError(startTime, "Start time required");
    valid = false;
  }

  // Validate end time
  const endTime = document.getElementById('endTime');
  if (!endTime.value) {
    showError(endTime, "End time required");
    valid = false;
  }

  if (!valid) e.preventDefault();
});

function showError(input, message) {
  const error = document.createElement('div');
  error.className = 'error-message';
  error.style.color = 'red';
  error.style.fontSize = '0.85em';
  error.textContent = message;
  input.parentNode.appendChild(error);
}
 