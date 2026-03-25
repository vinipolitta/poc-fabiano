ALTER TABLE form_templates
    ADD COLUMN slug VARCHAR(255);

UPDATE form_templates
SET slug = LOWER(REPLACE(name, ' ', '-'));

ALTER TABLE form_templates
    MODIFY slug VARCHAR(255) NOT NULL;

CREATE UNIQUE INDEX uk_form_templates_slug ON form_templates(slug);