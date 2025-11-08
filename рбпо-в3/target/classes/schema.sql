ALTER TABLE insurance
    ADD CONSTRAINT chk_payment_positive CHECK (payment >= 0);

-- Comment on table and columns (optional, for documentation)
COMMENT ON TABLE insurance IS 'Insurance policies and claims information';
COMMENT ON COLUMN insurance.policy IS 'Policy number';
COMMENT ON COLUMN insurance.customer IS 'Customer name';
COMMENT ON COLUMN insurance.claim IS 'Claim description';
COMMENT ON COLUMN insurance.payment IS 'Payment amount';
COMMENT ON COLUMN insurance.coverage IS 'Coverage type';