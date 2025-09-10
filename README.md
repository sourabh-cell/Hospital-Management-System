# 🏥 Hospital Management System (HMS)

A robust, modular, and secure backend system designed to streamline hospital operations across departments like Admin, Nurse, Accountant, Pharmacy, and Insurance. Built with Spring Boot, Hibernate, MySQL for secure role-based access and auditability.
---
## 🚀 Features

- **Modular Architecture**: Separate workflows for Admin, Doctor, Nurse, Accountant, Pharmacy, and Insurance
- **Secure Authentication**: Secure login with role-based access control
- **Audit Trails**: Metadata logging for all critical actions across modules
- **Observability**: Integrated with Prometheus and Grafana Alloy for real-time monitoring
- **Interactive Dashboards**: Role-specific views with actionable insights
- **Billing & Insurance Integration**: Seamless handling of patient billing, claims, and reimbursements
- **Pharmacy Management**: Inventory, prescriptions, and vendor tracking
- **Patient Workflow Optimization**: Separate handling for OPD and admitted cases

## 🧱 Tech Stack

| Layer         | Technology                     |
|--------------|--------------------------------|
| Backend       | Spring Boot, Hibernate         |
| Database      | MySQL                          |
| Auth & Security| Spring Security  |
| Observability | Prometheus, Grafana Alloy      |
| Deployment    | Railway, VPS, Nginx + SSL      |
| Documentation | Markdown      |

## 📁 Module Overview

### 1. Admin
- User & Role Management
- Department Setup
- Audit Log Viewer

### 2. Doctor
- Patient Diagnosis & Treatment Planning
- Prescription Management
- OPD Consultation & Follow-up Scheduling
- Access to Lab Reports & Medical History
- Discharge Summary & Referral Notes


### 3. Nurse
- Patient Admission & Discharge
- Vitals & Treatment Notes
- OPD vs Inpatient Workflow

### 4. Accountant
- Billing & Invoicing
- Payment Tracking
- Financial Reports

### 5. Pharmacy
- Inventory Management
- Prescription Fulfillment
- Vendor & Purchase Orders

### 6. Insurance
- Claim Submission
- Reimbursement Tracking
- Policy Validation

---

## ✅ Best Practices Followed
- Role-based access enforced at controller and service layers
- Audit metadata stored for all sensitive operations
- Color-coded, interactive documentation for clarity
- Hybrid deployment strategy with reverse proxy and SSL

## 🙌 Acknowledgment
This system is shaped by countless hours of studying real hospital workflows, compliance standards, and backend architecture best practices. Special thanks to:
- Healthcare professionals whose operational insights inspired each module
- Fellow developers and architects who value clarity, modularity, and accountability


