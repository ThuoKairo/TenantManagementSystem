# Tenant Management System — Navigation & Intents

**Course:** BBT 3.2: Mobile Application Development  
**Language:** Kotlin  
**Topic:** Navigation Practical 1 — Moving Between Screens with Explicit & Implicit Intents

---

## 📱 Project Overview

The **Tenant Management System** is a multi-screen Android application built with **Kotlin**, **View Binding**, and **Data Binding**. This version implements screen navigation using **Explicit Intents** (Login ↔ Register ↔ Main/Add Tenant) and **Implicit Intents** (opening external web browser & phone dialer).

---

## ✨ Features Implemented

1. **LoginActivity (Launcher Screen)**:
   - Primary entry point configured as `LAUNCHER` in `AndroidManifest.xml`.
   - Explicit Intent navigation to `MainActivity` upon login.
   - Explicit Intent navigation to `RegisterActivity`.
   - Implicit Intent (`ACTION_VIEW`) opening the web browser to Strathmore University (`https://www.strathmore.edu`).
   - Receives registered email passed from `RegisterActivity` via `getStringExtra("EMAIL")`.

2. **RegisterActivity**:
   - Account creation form (`FullName`, `Email`, `Password`).
   - Passes registered email back to `LoginActivity` using `putExtra("EMAIL", email)`.
   - Uses `Intent.FLAG_ACTIVITY_CLEAR_TOP` to return to the existing `LoginActivity` instance.

3. **MainActivity (Add Tenant Screen)**:
   - Data Binding layout (`activity_main.xml`) with `Tenant` data object.
   - Saves tenant details and renders summary reactively using `@{tenant.summary()}`.
   - **CALL TENANT Button**: Uses an implicit Intent (`ACTION_DIAL`, `Uri.parse("tel:${tenant.phone}")`) to open the system phone dialer with the saved tenant's phone number.

---

## 📁 Project Structure

```text
TenantManagementSystem
│
└── app
    │
    ├── manifests/
    │   └── AndroidManifest.xml   # Defines LoginActivity (Launcher), RegisterActivity, MainActivity
    │
    ├── src/main/java/com/example/tenantmanagementsystemgroupa/
    │   ├── LoginActivity.kt     # Handles login, website link, and navigation
    │   ├── RegisterActivity.kt  # Handles registration and passing email extra
    │   ├── MainActivity.kt      # Add Tenant screen with dialer functionality
    │   └── Tenant.kt            # Data class representing tenant entity
    │
    └── src/main/res/
        ├── layout/
        │   ├── activity_login.xml    # Login ConstraintLayout UI
        │   ├── activity_register.xml # Register ConstraintLayout UI
        │   └── activity_main.xml     # Data Binding Add Tenant UI
        └── values/
            └── strings.xml           # Centralized string resources
```

---

## 🚀 App Navigation Flow

```text
               ┌──────────────┐
               │   Browser    │  (Implicit Intent: ACTION_VIEW)
               └──────────────┘
                      ▲
                      │
   REGISTER  ◀──────▶ LOGIN (Launcher)
   Activity           │
                      ▼  (Explicit Intent)
               ┌──────────────┐        ┌──────────────┐
               │  ADD TENANT  │──────▶ │ Phone Dialer │  (Implicit Intent: ACTION_DIAL)
               │ (MainActivity)│        └──────────────┘
               └──────────────┘
```

---

## 🛠️ How to Test the Flow

1. Launch the app on your emulator — **`LoginActivity`** will open first.
2. Tap **`Need help? Visit our website`** to open the web browser to Strathmore University.
3. Tap **`Don't have an account? Register`**, fill in the form, and tap **`REGISTER`**.
4. You will return to `LoginActivity` with your email pre-filled!
5. Enter any password and tap **`LOGIN`** to open the **`Add Tenant`** screen.
6. Fill in Tenant details, tap **`SAVE`**, then tap **`CALL TENANT`** to open the phone dialer with the tenant's number.
