package com.example.classicmodlesslaes.model;

public enum OrderStatus {
    Shipped, InProcess, Disputed, OnHold, Resolved, Cancelled;

    public static OrderStatus fromDbValue(String dbValue) {
        switch (dbValue) {
            case "On Hold":
                return OnHold;
            case "Shipped":
                return Shipped;
            case "In Process":
                return InProcess;
            case "Disputed":
                return Disputed;
            case "Resolved":
                return Resolved;
            case "Cancelled":
                return Cancelled;
            default:
                throw new IllegalArgumentException("Unknown database value: " + dbValue);
        }
    }

    public String toDbValue() {
        switch (this) {
            case OnHold:
                return "On Hold";
            case Shipped:
                return "Shipped";
            case InProcess:
                return "In Process";
            case Disputed:
                return "Disputed";
            case Resolved:
                return "Resolved";
            case Cancelled:
                return "Cancelled";
            default:
                throw new IllegalArgumentException("Unknown enum value: " + this);
        }
    }
}
