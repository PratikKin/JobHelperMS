CREATE TABLE companies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    website VARCHAR(255),
    industry VARCHAR(255),
    location VARCHAR(255)
);

CREATE TABLE jobs (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    type VARCHAR(50),
    location VARCHAR(255),
    salary NUMERIC,
    company_id BIGINT REFERENCES companies(id),
    posted_at TIMESTAMP
);

CREATE TABLE feedbacks (
    id SERIAL PRIMARY KEY,
    candidate_id BIGINT NOT NULL,
    hr_id BIGINT NOT NULL,
    rating INT NOT NULL,
    comments TEXT,
    rejection_reason VARCHAR(100),
    given_at TIMESTAMP
);

CREATE TABLE applications (
    id SERIAL PRIMARY KEY,
    candidate_id BIGINT NOT NULL,
    job_id BIGINT NOT NULL,
    shortlisted BOOLEAN,
    hired BOOLEAN,
    applied_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE assessments (
    id SERIAL PRIMARY KEY,
    candidate_id BIGINT NOT NULL,
    test_type VARCHAR(100),
    score NUMERIC,
    verified BOOLEAN,
    taken_at TIMESTAMP
);
