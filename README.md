Project Overview

The Tenant Management System is an Android application. It demonstrates how to build a reactive Add Tenant form in Android using modern Jetpack architectural concepts: ConstraintLayout, View Binding and Data Binding.

---

Features Implemented

1. ConstraintLayout Form Interface:
   - Title: `TENANT MANAGEMENT SYSTEM`
   - Input fields for Tenant Name, Phone Number and Rent Paid.
   - SAVE Action Button.

2. View Binding Integration:
   - Type-safe, null-safe access to layout views inside `MainActivity.kt` without `findViewById()`.

3. Data Binding and Kotlin Data Class:
   - `Tenant.kt` data class representing a tenant entity with a custom formatted `summary()` method.
   - Declarative layout updates using Data Binding expressions (`@{tenant.summary()}`).

4. Input Validation:
   - Error feedback on the Tenant Name field if submitted blank.

---

Project Structure

```text
TenantManagementSystem
│
└── app
    │
    ├── src/main/java/com/example/tenantmanagementsystemgroupa/
    │   ├── MainActivity.kt   # Inflates layout, handles SAVE click, binds Tenant data
    │   └── Tenant.kt         # Kotlin data class holding tenant properties & summary()
    │
    └── src/main/res/
        ├── layout/
        │   └── activity_main.xml # ConstraintLayout wrapped in <layout> & <data> tags
        └── values/
            ├── strings.xml       # Centralized string resources
            └── themes.xml        # AppCompat theme definition
```

---

Tech Stack and Requirements

- Language: Kotlin
- Build System: Gradle (Kotlin DSL `build.gradle.kts`)
- Min SDK: 24 (Android 7.0)
- Target SDK: 37
- Libraries and Architecture:
  - `androidx.appcompat:appcompat`
  - `androidx.constraintlayout:constraintlayout`
  - Android Jetpack View Binding & Data Binding

---

How to Run the App

1. Open the project folder in Android Studio.
2. Sync the project with Gradle files (`File → Sync Project with Gradle Files`).
3. Start an Android Virtual Device (AVD) via `Tools → Device Manager` (e.g. Pixel 8).
4. Click the green Run button (or press `Shift + F10`).
5. Enter a Tenant Name, Phone Number and Rent Paid, then tap SAVE to view the generated summary.
