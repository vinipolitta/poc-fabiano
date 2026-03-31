CREATE TABLE attendance_records (
    id                  BIGINT      NOT NULL AUTO_INCREMENT,
    form_template_id    BIGINT      NOT NULL,
    attended            BOOLEAN     NOT NULL DEFAULT FALSE,
    attended_at         DATETIME    NULL,
    notes               TEXT        NULL,
    row_order           INT         NULL,
    created_at          DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    CONSTRAINT fk_attendance_template FOREIGN KEY (form_template_id)
        REFERENCES form_templates(id) ON DELETE CASCADE
);

CREATE TABLE attendance_record_data (
    record_id   BIGINT          NOT NULL,
    col_key     VARCHAR(255)    NOT NULL,
    col_value   TEXT            NULL,

    PRIMARY KEY (record_id, col_key),
    CONSTRAINT fk_att_data_record FOREIGN KEY (record_id)
        REFERENCES attendance_records(id) ON DELETE CASCADE
);
