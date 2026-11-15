# Tenant Management System

An Android app for landlords to record tenants, see total rent, and call tenants.

## Download
Latest APK: see **Releases** on the right of this page.

## Features
- Register and log in
- Dashboard with number of tenants and total rent
- Add tenants and view all tenants (saved with Room)
- Call a tenant, options menu and navigation drawer

## Architecture
`ui/`    Activities, Fragments and TenantViewModel (UI layer)  
`data/`  Tenant, TenantDao, TenantDatabase, TenantRepository (Data layer)

Screens → TenantViewModel (LiveData) → TenantRepository → Room

## Team
| Stage | Member | Admission No. | Pull request |
|---|---|---|---|
| 1 Lead | Thuo Kairo | 123456 | [Commit be8609f](https://github.com/ThuoKairo/TenantManagementSystem/commit/be8609f) |
| 2 ViewModel | Thuo Kairo | 123456 | [PR #2](https://github.com/ThuoKairo/TenantManagementSystem/pull/2) |
| 3 Room | Thuo Kairo | 123456 | [PR #3](https://github.com/ThuoKairo/TenantManagementSystem/pull/3) |
| 4 Screens | Thuo Kairo | 123456 | [PR #2](https://github.com/ThuoKairo/TenantManagementSystem/pull/2) |
| 5 Data integration | Thuo Kairo | 123456 | [PR #4](https://github.com/ThuoKairo/TenantManagementSystem/pull/4) |
| 6 Release | Thuo Kairo | 123456 | [PR #5](https://github.com/ThuoKairo/TenantManagementSystem/pull/5) |
