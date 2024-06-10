CREATE TABLE IF NOT EXISTS TOOL (
    tool_code VARCHAR(255) PRIMARY KEY,
    tool_type VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    daily_charge DECIMAL(10, 2) NOT NULL,
    weekday_charge BOOLEAN NOT NULL,
    weekend_charge BOOLEAN NOT NULL,
    holiday_charge BOOLEAN NOT NULL
);

