
TABLE_EXISTS=$(psql -U "$DB_USERNAME" -d "$DB_URL" -tAc "SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'users');")

if [ "$TABLE_EXISTS" = 'f' ]; then
  echo "Initializing database with data.sql"
  psql -U "$DB_USERNAME" -d "$DB_URL" -f /docker-entrypoint-initdb.d/data.sql
else
  echo "Database already initialized. Skipping data.sql execution."
fi
