## Part 1: Test it with SQL

## table techjobs.job

id int
employer VARCHAR(255)
name VARCHAR(255)
skills VARCHAR(255)

## Part 2: Test it with SQL

SELECT employer.name
FROM employer
WHERE employer.location = "St. Louis City";

## Part 3: Test it with SQL

DROP table job;

## Part 4: Test it with SQL

SELECT job.name, skill.description
FROM skill, job
INNER JOIN job_skills ON job.id = job_skills.jobs_id
WHERE skill.id = job_skills.skills_id
ORDER BY job.name ASC;