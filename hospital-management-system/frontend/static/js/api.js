const API_BASE = '/api';

function getToken() {
  return localStorage.getItem('hms_token');
}

function setToken(token) {
  localStorage.setItem('hms_token', token);
}

function setUser(user) {
  localStorage.setItem('hms_user', JSON.stringify(user));
}

function getUser() {
  try {
    return JSON.parse(localStorage.getItem('hms_user'));
  } catch {
    return null;
  }
}

function clearAuth() {
  localStorage.removeItem('hms_token');
  localStorage.removeItem('hms_user');
}

async function apiRequest(method, endpoint, body = null) {
  const token = getToken();
  const headers = { 'Content-Type': 'application/json' };
  if (token) headers['Authorization'] = `Bearer ${token}`;

  const opts = { method, headers };
  if (body) opts.body = JSON.stringify(body);

  const res = await fetch(API_BASE + endpoint, opts);

  if (res.status === 401) {
    clearAuth();
    window.location.href = '/index.html';
    return null;
  }

  const data = await res.json().catch(() => ({}));
  return { ok: res.ok, status: res.status, data };
}

const api = {
  post: (ep, body) => apiRequest('POST', ep, body),
  get:  (ep)       => apiRequest('GET', ep),
  put:  (ep, body) => apiRequest('PUT', ep, body),
  del:  (ep)       => apiRequest('DELETE', ep),
};

// Toast notifications
function showToast(message, type = 'info') {
  let container = document.querySelector('.toast-container');
  if (!container) {
    container = document.createElement('div');
    container.className = 'toast-container';
    document.body.appendChild(container);
  }
  const toast = document.createElement('div');
  toast.className = `toast toast-${type}`;
  toast.textContent = message;
  container.appendChild(toast);
  setTimeout(() => toast.remove(), 3500);
}

// Role guard
function requireAuth(allowedRoles = []) {
  const user = getUser();
  if (!user || !getToken()) {
    window.location.href = '/index.html';
    return null;
  }
  if (allowedRoles.length && !allowedRoles.includes(user.role)) {
    showToast('Access denied for your role', 'error');
    window.location.href = '/dashboard.html';
    return null;
  }
  return user;
}

// Sidebar active link
function setActivePage(page) {
  document.querySelectorAll('.nav-item').forEach(el => {
    el.classList.toggle('active', el.dataset.page === page);
  });
}

// Build sidebar based on role
function buildSidebar(role) {
  const nav = document.getElementById('sidebarNav');
  if (!nav) return;

  const links = [
    { page: 'dashboard',        icon: 'bi-grid-1x2',      label: 'Dashboard',        roles: ['admin','doctor','receptionist','patient'], href: '/dashboard.html' },
    { page: 'patients',         icon: 'bi-people',         label: 'Patients',         roles: ['admin','receptionist','doctor'],            href: '/patients.html'  },
    { page: 'doctors',          icon: 'bi-person-badge',   label: 'Doctors',          roles: ['admin'],                                    href: '/doctors.html'   },
    { page: 'appointments',     icon: 'bi-calendar-check', label: 'Appointments',     roles: ['admin','doctor','receptionist','patient'],  href: '/appointments.html' },
    { page: 'records',          icon: 'bi-file-medical',   label: 'Medical Records',  roles: ['admin','doctor','patient'],                 href: '/records.html'   },
    { page: 'billing',          icon: 'bi-receipt',        label: 'Billing',          roles: ['admin','receptionist','patient'],           href: '/billing.html'   },
    { page: 'admin',            icon: 'bi-shield-lock',    label: 'Admin Panel',      roles: ['admin'],                                    href: '/admin.html'     },
  ];

  nav.innerHTML = '';
  links.filter(l => l.roles.includes(role)).forEach(l => {
    const a = document.createElement('a');
    a.className = 'nav-item';
    a.dataset.page = l.page;
    a.href = l.href;
    a.innerHTML = `<i class="bi ${l.icon}"></i> ${l.label}`;
    nav.appendChild(a);
  });
}

function initPage(pageName, allowedRoles = []) {
  const user = requireAuth(allowedRoles);
  if (!user) return null;

  buildSidebar(user.role);
  setActivePage(pageName);

  const userEl = document.getElementById('topbarUser');
  if (userEl) userEl.textContent = user.username;

  const avatarEl = document.getElementById('topbarAvatar');
  if (avatarEl) avatarEl.textContent = user.username.charAt(0).toUpperCase();

  const roleEl = document.getElementById('topbarRole');
  if (roleEl) roleEl.textContent = user.role;

  document.getElementById('logoutBtn')?.addEventListener('click', () => {
    clearAuth();
    window.location.href = '/index.html';
  });

  return user;
}
