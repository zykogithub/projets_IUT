SELECT bytes/(1024*1024) AS utilis�, max_bytes/(1024*1024) AS maximum
FROM dba_ts_quotas
WHERE username = USER;