
      function validateField(field) {
        let value = field.value.trim();
        let errorElement = field.parentElement.querySelector("p.error");
        errorElement.textContent = ""; // clear old error

        switch (field.id) {
        //   case "bedId":
        //     if (!/^[0-9]+$/.test(value)) {
        //       errorElement.textContent = "Bed ID must be numbers only.";
        //     }
        //     break;

          case "patientName":
            if (!/^[A-Za-z\s]+$/.test(value)) {
              errorElement.textContent = "Patient Name must contain only letters.";
            }
            break;

          case "department":
            if (value === "") {
              errorElement.textContent = "Please select a department.";
            }
            break;

          case "relatedDepartment":
            // Optional, no error if empty
            if (value !== "" && !["ward", "ici", "ot"].includes(value)) {
              errorElement.textContent = "Invalid room selection.";
            }
            break;
        }
      }

      // Validate on blur (when user leaves field)
      document.querySelectorAll("#bedForm input, #bedForm select").forEach((input) => {
        input.addEventListener("blur", function () {
          validateField(this);
        });
      });

      // Validate on submit
      document.getElementById("bedForm").addEventListener("submit", function (e) {
        e.preventDefault();
        let valid = true;

        document.querySelectorAll("#bedForm input, #bedForm select").forEach((input) => {
          validateField(input);
          let error = input.parentElement.querySelector("p.error").textContent;
          if (error !== "") valid = false;
        });

        if (valid) {
          alert("Form submitted successfully!");
          this.submit();
        }
      });


      // new-bed-manager.js

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("bedForm");

  form.addEventListener("submit", function (event) {
    event.preventDefault(); // prevent page refresh

    // Get selected status (radio button)
    const status = document.querySelector('input[name="status"]:checked').value;

    // Build the object with form data
    const bedData = {
      bedId: document.getElementById("bedId").value,
      patientName: document.getElementById("patientName").value,
      department: document.getElementById("department").value,
      relatedDepartment: document.getElementById("relatedDepartment").value,
      status: status,
    };

    // Show in console (for testing)
    console.log("Saved Bed Data:", bedData);

    // Example: Success alert
    alert("Bed data saved successfully!");
  });
});

