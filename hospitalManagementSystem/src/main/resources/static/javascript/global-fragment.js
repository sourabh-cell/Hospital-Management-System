console.log('under global-fragment.js');

// ========================== ROUTES CONFIG ==========================
const routes = {
  '/dashboard/admin': {
    endpoint: '/fragment/dashboard/admin',
    css: [''],
    js: ['']
  },
  //******************Ambulance******************
  '/ambulance/add': {
    endpoint: '/fragment/ambulance/add',
    css: ['/css/ambulance-add.css'],
    js: ['/javascript/ambulance-add.js']
  },
  '/ambulance/view': {
    endpoint: '/fragment/ambulance/view',
    css: ['/css/ambulance-view.css'],
    js: []
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
  },
  //******************Registration******************
  '/register': {
    endpoint: '/fragment/register',
    css: ['/css/registration.css'],
    js: ['/javascript/registration.js']
  },
  //******************Department******************
  '/department/registration': {
    endpoint: '/fragment/department/registration',
    css: [''],
    js: ['']
  },
  '/department/list': {
    endpoint: '/fragment/department/list',
    css: [''],
    js: ['']
  },
  '/department/update/{id}': {
    endpoint: '/fragment/department/update/{id}',
    css: [''],
    js: ['']
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
      console.log('Fragment loaded:', endpoint);

      // 🔹 Run global message hide logic if available
      if (typeof initMessageHide === 'function') {
        initMessageHide();
      }

      // Remove old fragment CSS
      document.querySelectorAll('link[data-fragment]').forEach(link => link.remove());

      // Add new fragment CSS
      cssPaths.forEach(path => {
        if (path && !document.querySelector(`link[href="${path}"]`)) {
          const css = document.createElement('link');
          css.rel = 'stylesheet';
          css.href = path;
          css.setAttribute('data-fragment', 'true');
          document.head.appendChild(css);
          console.log('css added:', path);
        }
      });

      // Remove old JS before loading new ones
      document.querySelectorAll('script[data-fragment]').forEach(script => script.remove());

      // Add new JS
      jsPaths.forEach(path => {
        if (path) {
          const js = document.createElement('script');
          js.src = path;
          js.defer = true;
          js.setAttribute('data-fragment', 'true');
          document.body.appendChild(js);
          console.log('js added:', path);
        }
      });

      // Reinitialize AJAX forms for this fragment
      initAjaxForms('main-content');
    })
    .catch(err => console.error('Error loading fragment:', err));
}

// ========================== NAVIGATION ==========================
function navigateTo(url) {
  let routeKey = Object.keys(routes).find(key => {
    if (key.includes('{id}')) {
      return url.startsWith(key.replace('{id}', ''));
    }
    return key === url;
  });

  if (routeKey) {
    let endpoint = routes[routeKey].endpoint;

    if (endpoint.includes('{id}')) {
      const id = url.substring(url.lastIndexOf('/') + 1);
      endpoint = endpoint.replace('{id}', id);
    }

    loadFragment(endpoint, routes[routeKey].css, routes[routeKey].js);
  } else {
    console.error('No route found for:', url);
  }

  history.pushState(null, '', url);
}

// ========================== AJAX FORM HANDLER ==========================
function initAjaxForms(containerId = 'main-content') {
  const container = document.getElementById(containerId);
  if (!container) return;

  container.querySelectorAll('form[data-ajax="true"]').forEach(form => {
    if (form.dataset.ajaxBound) return;
    form.dataset.ajaxBound = 'true';

    form.addEventListener('submit', function (e) {
      e.preventDefault();

      const formData = new FormData(form);
      const fragmentPath = form.dataset.fragmentPath;

      fetch(form.action, {
        method: form.method || 'POST',
        body: formData
      })
        .then(res => res.text())
        .then(html => {
          container.innerHTML = html;
          initAjaxForms(containerId); // rebind forms

          // ✅ Re-inject JS for this fragment
          if (fragmentPath && routes[fragmentPath]) {
            const route = routes[fragmentPath];
            console.log('Reinitializing JS after POST for:', fragmentPath);

            // Remove old JS
            document.querySelectorAll('script[data-fragment]').forEach(s => s.remove());

            // Inject new JS
            route.js.forEach(path => {
              if (path) {
                const script = document.createElement('script');
                script.src = path;
                script.defer = true;
                script.setAttribute('data-fragment', 'true');
                document.body.appendChild(script);
                console.log('Re-added JS after POST:', path);
              }
            });

            // Re-add CSS if needed
            route.css.forEach(path => {
              if (path && !document.querySelector(`link[href="${path}"]`)) {
                const css = document.createElement('link');
                css.rel = 'stylesheet';
                css.href = path;
                css.setAttribute('data-fragment', 'true');
                document.head.appendChild(css);
                console.log('Re-added CSS after POST:', path);
              }
            });
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
    navigateTo('/logout'); // default page
  }
});

// ========================== DATA-NAV CLICK HANDLER ==========================
document.addEventListener('click', function (e) {
  const link = e.target.closest('[data-nav]');
  if (!link) return;

  e.preventDefault();
  const navUrl = link.getAttribute('data-nav');
  console.log('Navigating to:', navUrl);
  navigateTo(navUrl);
});
