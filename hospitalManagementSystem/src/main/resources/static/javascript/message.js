window.addEventListener('DOMContentLoaded', (event) => {
    setTimeout(() => {
        const errorDiv = document.getElementById('errorMsg');
        if (errorDiv) errorDiv.style.display = 'none';

        const successDiv = document.getElementById('successMsg');
        if (successDiv) successDiv.style.display = 'none';


    }, 5000);
});
