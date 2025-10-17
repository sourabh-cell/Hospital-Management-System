document.addEventListener("DOMContentLoaded", function () {
    console.log("In area")
    const roleSelect = document.getElementById("role");
    const dobInput = document.getElementById("dob");
    const ageInput = document.getElementById("age");
    const resetBtn = document.getElementById("resetBtn");
    let stateChangeListenerAdded = false;

console.log("Selected role value:", roleSelect.value);


    const roleSections = {
        DOCTOR: "doctorFields",
        LABORATORIST: "labFields",
        ACCOUNTANT: "accountantFields",
        RECEPTIONIST: "receptionistFields",
        HEADNURSE: "headNurseFields",
        PHARMACIST: "pharmacistFields",
        HR:"hrFields",
        INSURANCE:"insurerFields",

    };

    function hideAllSections() {
        Object.values(roleSections).forEach(id => {
            const el = document.getElementById(id);
            if (el) el.classList.add("d-none");
        });
    }

    function toggleRoleFields() {
        hideAllSections();

        const selectedOption = roleSelect.options[roleSelect.selectedIndex];
        const roleName = selectedOption.getAttribute("data-role-name");
        console.log("printing role name",roleName);

        // Normalize role name if needed (e.g., lowercase or remove spaces)
        const sectionId = roleSections[roleName];

        console.log("printing role field", sectionId);

        if (sectionId) {
            const section = document.getElementById(sectionId);
            if (section) {
                section.classList.remove("d-none");
            }
        }
    }


// Password validation

  const passwordInput = document.getElementById("password");
  const uppercaseRule = document.getElementById("uppercaseRule");
  const numberRule = document.getElementById("numberRule");
  const specialRule = document.getElementById("specialRule");

  passwordInput.addEventListener("input", function () {
    const value = this.value;

    // Rule 1: At least one uppercase letter
    uppercaseRule.style.color = /[A-Z]/.test(value) ? "green" : "red";

    // Rule 2: At least one number
    numberRule.style.color = /\d/.test(value) ? "green" : "red";

    // Rule 3: At least one special character
    // Match only these special characters: @$!%*?&#~
      const hasSpecial = /[@$!%*?&#~]/.test(value);

      specialRule.style.color = hasSpecial ? "green" : "red";

  });


  // Confirm password match check
    const confirmInput = document.getElementById("confirmPassword");
    const matchMessage = document.createElement("div");
    matchMessage.className = "form-text mt-1";
    confirmInput.parentNode.appendChild(matchMessage);

    function checkMatch() {
      const password = passwordInput.value;
      const confirm = confirmInput.value;

      if (confirm === "") {
        matchMessage.textContent = "";
        return;
      }

      if (password === confirm) {
        matchMessage.textContent = "✅ Passwords match";
        matchMessage.style.color = "green";
      } else {
        matchMessage.textContent = "❌ Passwords do not match";
        matchMessage.style.color = "red";
      }
    }

    passwordInput.addEventListener("input", checkMatch);
    confirmInput.addEventListener("input", checkMatch);





    // Calculate age based on date of birth
    // If date of birth is not provided, clear age input field
    // If date of birth is invalid, do not update age input field


    function calculateAge() {
        if (!dobInput.value) {
            ageInput.value = "";
            return;
        }
        
        const dob = new Date(dobInput.value);
        const today = new Date();
        
        // Validate date
        if (dob > today) {
            alert("Date of birth cannot be in the future!");
            dobInput.value = "";
            ageInput.value = "";
            return;
        }
        
        if (dob.getFullYear() < 1900) {
            alert("Please enter a valid date of birth!");
            dobInput.value = "";
            ageInput.value = "";
            return;
        }
        
        let age = today.getFullYear() - dob.getFullYear();
        const m = today.getMonth() - dob.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < dob.getDate())) {
            age--;
        }
        
        if (age < 0) {
            ageInput.value = "";
            return;
        }
        
        ageInput.value = age;
    }



    roleSelect.addEventListener("change", toggleRoleFields);
    dobInput.addEventListener("change", calculateAge);


    // Reset all fields when reset button is clicked

    resetBtn.addEventListener("click", function () {
        hideAllSections();
        ageInput.value = "";
        dobInput.value = "";
        document.getElementById("stateDropdown").selectedIndex = 0;
        document.getElementById("districtDropdown").innerHTML = '<option value="">Select District</option>';
    });

    // Helper function to set loading state for dropdowns
    function setLoading(dropdown, loading) {
        if (loading) {
            dropdown.disabled = true;
            const loadingOption = document.createElement("option");
            loadingOption.value = "";
            loadingOption.textContent = "Loading...";
            loadingOption.selected = true;
            dropdown.innerHTML = "";
            dropdown.appendChild(loadingOption);
        } else {
            dropdown.disabled = false;
        }
    }

    // State–District dropdown logic
    const stateDropdown = document.getElementById("stateDropdown");
    const districtDropdown = document.getElementById("districtDropdown");

    // Set initial loading state
    setLoading(stateDropdown, true);
    setLoading(districtDropdown, true);

    fetch("/api/data/states")
        .then(res => {
            if (!res.ok) throw new Error('Failed to load states');
            return res.json();
        })
        .then(data => {
            // Clear loading state and populate states
            stateDropdown.innerHTML = '<option value="">Select State</option>';
            stateDropdown.disabled = false;

            data.forEach(entry => {
                const option = document.createElement("option");
                option.value = entry.state;
                option.textContent = entry.state;
                stateDropdown.appendChild(option);
            });

            // Reset district dropdown
            districtDropdown.innerHTML = '<option value="">Select District</option>';
            districtDropdown.disabled = false;

            // Add event listener only once to prevent memory leaks
            if (!stateChangeListenerAdded) {
                stateDropdown.addEventListener("change", function () {
                    const selectedState = this.value;

                    if (!selectedState) {
                        districtDropdown.innerHTML = '<option value="">Select District</option>';
                        districtDropdown.disabled = false;
                        return;
                    }

                    // Show loading spinner for districts
                    setLoading(districtDropdown, true);

                    // Simulate async operation with setTimeout to show spinner
                    setTimeout(() => {
                        const districts = data.find(s => s.state === selectedState)?.districts || [];

                        districtDropdown.innerHTML = '<option value="">Select District</option>';
                        districts.forEach(d => {
                            const opt = document.createElement("option");
                            opt.value = d;
                            opt.textContent = d;
                            districtDropdown.appendChild(opt);
                        });

                        districtDropdown.disabled = false;
                    }, 300); // Small delay to show loading state
                });
                stateChangeListenerAdded = true;
            }
        })
        .catch(error => {
            console.error('Error:', error);
            
            // Re-enable dropdowns on error
            stateDropdown.disabled = false;
            districtDropdown.disabled = false;
            stateDropdown.innerHTML = '<option value="">Failed to load states</option>';
            districtDropdown.innerHTML = '<option value="">Select District</option>';
            
            // Show error message to user
            const errorDiv = document.createElement('div');
            errorDiv.className = 'alert alert-danger';
            errorDiv.textContent = 'Failed to load location data. Please try again later.';
            document.querySelector('form').prepend(errorDiv);
        });

        toggleRoleFields();

        // ---------------------------
        //  Name field validation
        // ---------------------------
        const firstNameInput = document.querySelector('input[th\\:field="*{firstName}"]') || document.getElementById("firstName");
        const lastNameInput  = document.querySelector('input[th\\:field="*{lastName}"]') || document.getElementById("lastName");


          [firstNameInput, lastNameInput].forEach(input => {
            input.addEventListener('keypress', (e) => {
              if (!/[a-zA-Z\s]/.test(e.key)) {
                e.preventDefault();
              }
            });
          });




//        function validateNameField(input) {
//            const regex = /^[A-Za-z\s]+$/; // Sirf alphabets aur spaces allowed
//            const existingError = input.parentElement.querySelector(".text-danger.name-error");
//            if (existingError) existingError.remove(); // Purana error remove
//
//            if (input.value.trim() === "") return; // Empty hone par kuch mat dikhao
//
//            if (!regex.test(input.value)) {
//                const error = document.createElement("div");
//                error.className = "text-danger small name-error";
//                error.textContent = "❌ Only alphabets allowed";
//                input.parentElement.appendChild(error);
//            }
//        }
//
//        // Event listeners
//        [firstNameInput, lastNameInput].forEach(input => {
//            if (input) {
//                input.addEventListener("input", () => validateNameField(input));
//                input.addEventListener("blur", () => validateNameField(input));
//            }
//        });

});