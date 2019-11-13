# Getting Started

### Create new database (if not existed)

```sql
DROP DATABASE `libraryapp`;
CREATE SCHEMA `libraryapp` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
```

### Local environment settings

This guide-lines is used for IntelliJ IDE.

#### Step 1
Select menu `Run` > `Edit Configurations...`

#### Step 2
Click `Browse` from `Environment variables` in `Configuration` tab

#### Step 3

Add these variables:

```
DATABASE_URL=jdbc:mysql://localhost:3306/libraryapp?reconnect=true&useUnicode=true&characterEncoding=utf8
DATABASE_USERNAME=root
DATABASE_PASSWORD=123456
JWT_SECRET=test.secret
```