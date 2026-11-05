package com.personalfinance.dto;
public record ApiResponse<T>(String message, T data) {}
