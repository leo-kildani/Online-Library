cd src/main/resources/scripts

read -p "Enter MySQL username (recommended: root): " DBUSER
read -s -p "Enter MySQL password: " DBPWD
echo

for file in *.sql; do
    mysql -u$DBUSER -p$DBPWD < "$file"
done