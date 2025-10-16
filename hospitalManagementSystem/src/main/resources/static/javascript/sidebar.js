document.addEventListener("DOMContentLoaded", function () {
  // Sidebar minimize
  document
    .querySelectorAll('[data-bs-toggle="minimize"]')
    .forEach(function (el) {
      el.addEventListener("click", function () {
        document.body.classList.toggle("sidebar-icon-only");
      });
    });

  // Sidebar offcanvas (for mobile)
  document
    .querySelectorAll('[data-bs-toggle="offcanvas"]')
    .forEach(function (el) {
      el.addEventListener("click", function () {
        document.querySelector(".sidebar-offcanvas").classList.toggle("active");
      });
    });
});

(function () {
  function isCollapsed() {
    return (
      document.body.classList.contains("sidebar-icon-only") ||
      document.documentElement.classList.contains("sidebar-icon-only") ||
      (document.querySelector(".container-scroller") &&
        document
          .querySelector(".container-scroller")
          .classList.contains("sidebar-icon-only"))
    );
  }

  function showCollapse(el) {
    if (!el) return;
    el.classList.add("show");
    el.style.display = "block";
    el.setAttribute("aria-expanded", "true");
  }
  function hideCollapse(el) {
    if (!el) return;
    el.classList.remove("show");
    el.style.display = "none";
    el.setAttribute("aria-expanded", "false");
  }

  function attachHoverHandlers() {
    document
      .querySelectorAll(".sidebar .nav .nav-item")
      .forEach(function (item) {
        if (item.__hover_attached) return;
        item.__hover_attached = true;

        var directCollapse = item.querySelector(":scope > .collapse");
        if (directCollapse) {
          item.addEventListener("mouseenter", function () {
            if (!isCollapsed()) return;
            item.classList.add("hover-open");
            showCollapse(directCollapse);

            directCollapse.querySelectorAll(".collapse").forEach(function (c) {
              c.classList.remove("show");
              c.style.display = "none";
              c.style.position = "absolute";
              c.style.top = "0";
              c.style.left = directCollapse.offsetWidth + "px";
              c.style.width = directCollapse.offsetWidth + "px";
              c.style.zIndex = 10000;
            });
          });

          item.addEventListener("mouseleave", function () {
            if (!isCollapsed()) return;
            item.classList.remove("hover-open");
            hideCollapse(directCollapse);

            directCollapse.querySelectorAll(".collapse").forEach(function (c) {
              hideCollapse(c);
            });
          });

          directCollapse
            .querySelectorAll(".nav-item")
            .forEach(function (child) {
              var innerCollapse = child.querySelector(":scope > .collapse");
              if (!innerCollapse) return;

              child.addEventListener("mouseenter", function () {
                if (!isCollapsed()) return;
                child.classList.add("hover-open");
                innerCollapse.style.position = "absolute";
                innerCollapse.style.top = "0";
                innerCollapse.style.left =
                  directCollapse.offsetWidth - 1 + "px";
                innerCollapse.style.width = directCollapse.offsetWidth + "px";
                innerCollapse.style.zIndex = 11000;
                showCollapse(innerCollapse);
              });

              child.addEventListener("mouseleave", function () {
                if (!isCollapsed()) return;
                child.classList.remove("hover-open");
                hideCollapse(innerCollapse);
              });
            });
        }
      });

    document.addEventListener(
      "click",
      function (e) {
        var trigger = e.target.closest(
          'a[data-bs-toggle="collapse"], [data-bs-target][data-bs-toggle="collapse"]'
        );
        if (!trigger) return;
        if (isCollapsed()) {
          e.preventDefault();
          e.stopPropagation();
        }
      },
      true
    );
  }

  document.addEventListener("DOMContentLoaded", function () {
    attachHoverHandlers();

    var observer = new MutationObserver(function (muts) {
      muts.forEach(function (m) {
        if (m.attributeName === "class") {
          if (!isCollapsed()) {
            document
              .querySelectorAll(".sidebar .collapse")
              .forEach(function (el) {
                el.classList.remove("show");
                el.style.display = "";
              });
            document
              .querySelectorAll(".sidebar .nav-item.hover-open")
              .forEach(function (el) {
                el.classList.remove("hover-open");
              });
          } else {
            document
              .querySelectorAll(".sidebar .collapse")
              .forEach(function (el) {
                el.classList.remove("show");
                el.style.display = "none";
              });
          }
        }
      });
    });
    observer.observe(document.body, {
      attributes: true,
      attributeFilter: ["class"],
    });
  });
})();
