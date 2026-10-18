# Tenant Management System — Navigation, Lifecycles & Fragments

**Course:** BBT 3.2: Mobile Application Development  
**Language:** Kotlin  
**Topic:** Navigation Practical 2 — Activity Lifecycles, State Persistence & Navigation Fragments

---

## 📱 Project Overview

The **Tenant Management System** is a multi-screen Android application developed using **Kotlin**, **Jetpack Navigation**, **View Binding**, and **Data Binding**. This version incorporates:
- Activity lifecycle logging & `onSaveInstanceState` state persistence across screen rotations.
- Safe Args & Navigation Component configuration.
- Shared data model (`Tenant` implementing `Serializable`) and `TenantRepository` object.
- Fragment screens (`DashboardFragment`, `AddTenantFragment`, `ViewTenantsFragment`) mapped inside a Navigation Graph (`nav_graph.xml`).

---

## ✨ Features Implemented

1. **Activity Lifecycle & State Persistence**:
   - Added `Log.d("TENANT_LIFECYCLE", ...)` to all 7 lifecycle callbacks in `MainActivity.kt` (`onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onRestart`, `onDestroy`).
   - Implemented `onSaveInstanceState(outState: Bundle)` and state restoration in `onCreate` to preserve tenant data during screen rotations.

2. **Navigation Component & Safe Args**:
   - Added `androidx.navigation.safeargs.kotlin` plugin (v2.9.8).
   - Added `navigation-fragment-ktx` and `navigation-ui-ktx` dependencies.

3. **Shared Data Layer**:
   - `Tenant.kt`: Made `Tenant` class implement `java.io.Serializable` for Safe Args passing.
   - `TenantRepository.kt`: Kotlin `object` maintaining an in-memory list of tenants (`mutableListOf<Tenant>()`) and total rent calculations.

4. **Fragment Screens**:
   - **`DashboardFragment`**: Property overview displaying total tenant count and total formatted rent (`KSh %,.0f`).
   - **`AddTenantFragment`**: Form screen for collecting new tenant details.
   - **`ViewTenantsFragment`**: Displays list of added tenants and optional "Just added" highlight using Data Binding and `Group` visibility.

5. **Navigation Graph (`nav_graph.xml`)**:
   - Configured `dashboardFragment` as `startDestination`.
   - Defined navigation actions connecting Dashboard ➔ Add Tenant ➔ View Tenants.
   - Configured nullable `newTenant` argument (`app:argType="Tenant"`) on `viewTenantsFragment`.

---

## 📁 Project Architecture

```text
TenantManagementSystem
│
└── app
    │
    ├── src/main/java/com/example/tenantmanagementsystemgroupa/
    │   ├── LoginActivity.kt        # Login screen & intent handling
    │   ├── RegisterActivity.kt     # Account creation screen
    │   ├── MainActivity.kt         # Lifecycle logs & state restoration
    │   ├── DashboardFragment.kt    # Dashboard metric overview
    │   ├── AddTenantFragment.kt    # Add tenant form fragment
    │   ├── ViewTenantsFragment.kt  # Tenant list fragment
    │   ├── Tenant.kt               # Serializable data class
    │   └── TenantRepository.kt     # In-memory tenant repository
    │
    └── src/main/res/
        ├── layout/
        │   ├── activity_login.xml
        │   ├── activity_register.xml
        │   ├── activity_main.xml
        │   ├── fragment_dashboard.xml
        │   ├── fragment_add_tenant.xml
        │   └── fragment_view_tenants.xml
        │
        └── navigation/
            └── nav_graph.xml      # Navigation graph destinations & actions
```

---

## 🚀 How to Run & Verify

1. Launch the app on your emulator — `LoginActivity` will open first.
2. Log in to navigate to `MainActivity`.
3. In Logcat (`tag:TENANT_LIFECYCLE`), observe lifecycle transition logs.
4. Rotate the emulator to verify that saved tenant details persist across screen rotation.
