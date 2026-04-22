// ============================================================
// Student Planner — Service Worker
// Handles push events and app shell caching.
// ============================================================

const CACHE_NAME = "planner-v1";
const OFFLINE_URLS = [
  "/student-planner/login.html",
  "/student-planner/dashboard.html",
  "/student-planner/assignments.html",
  "/student-planner/exams.html",
  "/student-planner/manifest.json",
  "/student-planner/icon.png"
];

// ── Install: pre-cache the app shell ─────────────────────────
self.addEventListener("install", (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => cache.addAll(OFFLINE_URLS))
  );
  self.skipWaiting();
});

// ── Activate: clean up old caches ────────────────────────────
self.addEventListener("activate", (event) => {
  event.waitUntil(
    caches.keys().then((keys) =>
      Promise.all(
        keys.filter((k) => k !== CACHE_NAME).map((k) => caches.delete(k))
      )
    )
  );
  self.clients.claim();
});

// ── Fetch: network-first, cache fallback ─────────────────────
self.addEventListener("fetch", (event) => {
  if (event.request.method !== "GET") return;
  event.respondWith(
    fetch(event.request).catch(() => caches.match(event.request))
  );
});

// ── Push: handle server-sent push payloads (FCM upgrade path) ─
self.addEventListener("push", (event) => {
  const data = event.data ? event.data.json() : {};
  const title = data.title || "📚 Student Planner";
  const options = {
    body: data.body || "You have an upcoming deadline.",
    icon: "/student-planner/icon.png",
    badge: "/student-planner/icon.png",
    tag: data.tag || "planner-reminder",
    renotify: true,
    data: { url: data.url || "/student-planner/dashboard.html" }
  };
  event.waitUntil(self.registration.showNotification(title, options));
});

// ── Message: triggered by dashboard JS to fire local notifications ─
self.addEventListener("message", (event) => {
  if (event.data && event.data.type === "SHOW_NOTIFICATION") {
    const { title, body, tag } = event.data;
    self.registration.showNotification(title, {
      body,
      icon: "/student-planner/icon.png",
      badge: "/student-planner/icon.png",
      tag,
      renotify: false,
      vibrate: [200, 100, 200],
      data: { url: "/student-planner/dashboard.html" }
    });
  }
});

// ── Notification click: open the app ─────────────────────────
self.addEventListener("notificationclick", (event) => {
  event.notification.close();
  const targetUrl = (event.notification.data && event.notification.data.url)
    ? event.notification.data.url
    : "/student-planner/dashboard.html";
  event.waitUntil(
    clients.matchAll({ type: "window", includeUncontrolled: true }).then((clientList) => {
      for (const client of clientList) {
        if (client.url.includes("student-planner") && "focus" in client) {
          return client.focus();
        }
      }
      return clients.openWindow(targetUrl);
    })
  );
});
