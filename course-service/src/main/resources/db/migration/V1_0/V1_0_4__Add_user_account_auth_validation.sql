ALTER TABLE user ADD COLUMN is_account_non_expired BOOLEAN NOT NULL;
ALTER TABLE user ADD COLUMN is_account_non_locked BOOLEAN NOT NULL;
ALTER TABLE user ADD COLUMN is_credentials_non_expired BOOLEAN NOT NULL;
ALTER TABLE user ADD COLUMN is_enabled BOOLEAN NOT NULL;