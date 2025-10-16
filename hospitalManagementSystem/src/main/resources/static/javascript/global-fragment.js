console.log('under global-fragment.js');

// ========================== ROUTES CONFIG ==========================
const routes = {
  '/dashboard/admin': {
    endpoint: '/fragment/dashboard/admin',
    css: [''],
    js: ['']
  },
  '/ambulance/add': {
    endpoint: '/fragment/ambulance/add',
    css: ['/css/ambulance-add.css'],
    js: ['/javascript/ambulance-add.js']
  },
  '/ambulance/view': {
    endpoint: '/fragment/ambulance/view',
    css: ['/css/ambulance-view.css'],
    js: ['']
  },
  '/driver/add': {
    endpoint: '/fragment/driver/add',
    css: ['/css/driver-add.css'],
    js: ['/javascript/driver-add.js']
  },
  '/ambulance/assignment/add': {
    endpoint: '/fragment/ambulance/assignment',
    css: ['/css/ambulance-assignment.css'],
    js: ['/javascript/ambulance-assignment.js']
  }
};

let currentFragmentUrl = '';

// ========================== FRAGMENT LOADER ==========================
function loadFragment(endpoint, cssPaths = [], jsPaths = []) {
    if (currentFragmentUrl === endpoint) return;
    currentFragmentUrl = endpoint;

    fetch(endpoint)
        .then(res => res.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;

            // Remove old fragment CSS
            document.querySelectorAll('link[data-fragment]').forEach(link => link.remove());

            // Add new fragment CSS
            cssPaths.forEach(path => {
                if (!document.querySelector(`link[href="${path}"]`)) {
                    const css = document.createElement('link');
                    css.rel = 'stylesheet';
                    css.href = path;
                    css.setAttribute('data-fragment', 'true');
                    document.head.appendChild(css);
                }
            });

            // Add fragment JS
            jsPaths.forEach(path => {
                if (!document.querySelector(`script[src="${path}"]`)) {
                    const js = document.createElement('script');
                    js.src = path;
                    js.defer = true;
                    document.body.appendChild(js);
                }
            });

            // Initialize AJAX forms in the loaded fragment
            initAjaxForms('main-content');
        })
        .catch(err => console.error('Error loading fragment:', err));
}

// ========================== NAVIGATION ==========================
function navigateTo(url) {
    history.pushState(null, '', url);
    const route = routes[url];
    if (route) {
        loadFragment(route.endpoint, route.css, route.js);
    }
}

// ========================== AJAX FORM HANDLER ==========================
function initAjaxForms(containerId = 'main-content') {
    const container = document.getElementById(containerId);
    if (!container) return;

    container.querySelectorAll('form[data-ajax="true"]').forEach(form => {
        if (form.dataset.ajaxBound) return; // avoid binding multiple times
        form.dataset.ajaxBound = 'true';

        form.addEventListener('submit', function (e) {
            e.preventDefault();

            const formData = new FormData(form);

            fetch(form.action, {
                method: form.method || 'POST',
                body: formData
            })
            .then(res => res.text())
            .then(html => {
                // Replace main content with returned fragment
                container.innerHTML = html;

                // Re-initialize AJAX forms in the new content
                initAjaxForms(containerId);

                // Optionally, re-run JS/CSS specific to the fragment
                const fragmentPath = form.dataset.fragmentPath;
                if (fragmentPath && routes[fragmentPath]) {
                    const r = routes[fragmentPath];
                    loadFragment(r.endpoint, r.css, r.js);
                }
            })
            .catch(err => console.error('Error submitting form:', err));
        });
    });
}

// ========================== POPSTATE HANDLER ==========================
window.addEventListener('popstate', () => {
    const path = window.location.pathname;
    if (routes[path]) {
        loadFragment(routes[path].endpoint, routes[path].css, routes[path].js);
    }
});

// ========================== INITIAL LOAD ==========================
window.addEventListener('DOMContentLoaded', () => {
    const path = window.location.pathname;
    if (routes[path]) {
        loadFragment(routes[path].endpoint, routes[path].css, routes[path].js);
    } else {
        navigateTo('/dashboard/admin'); // default page
    }
});
