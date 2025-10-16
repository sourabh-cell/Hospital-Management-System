console.log("ambulance-view.js loaded");

function loadTabData(tabId, url) {
  console.log("Loading tab:", tabId, "URL:", url);
  $(tabId).load(url, function (response, status, xhr) {
    if (status === "error") {
      console.error("Error loading data:", xhr.status, xhr.statusText);
    }
  });
}

// Use Bootstrap's tab show event instead of click
document.addEventListener('DOMContentLoaded', function() {
  var tabElms = document.querySelectorAll('a[data-bs-toggle="tab"]');
  
  tabElms.forEach(function(tabEl) {
    tabEl.addEventListener('shown.bs.tab', function (event) {
      var targetId = event.target.getAttribute('href'); // gets the tab pane id
      
      // Map tab pane IDs to their respective URLs
      var urlMap = {
        '#ambulanceData': '/ambulance/list',
        '#driverData': '/driver/list',
        '#assignmentData': '/assignment/list',
        '#assignmentHistoryData': '/assignment/history'
      };
      
      // Only load if the tab content is empty or needs refreshing
      if (urlMap[targetId] && ($(targetId).is(':empty') || $(targetId).text().trim() === 'Loading...')) {
        console.log("Loading tab:", targetId);
        loadTabData(targetId, urlMap[targetId]);
      }
    });
  });
  
  // Load initial tab content
  console.log("Loading default ambulance list...");
  loadTabData("#ambulanceData", "/ambulance/list");
});
