import mysql.connector
import random
import names
from faker import Faker
import hashlib
import string
import random


def initialize_bank(mycursor):
    for i in mycursor.execute("SHOW DATABASES", multi=True):  # IF THE DATABASE ALREADY EXISTS, THEN DROP IT
        f = (('dukh_bank',) in i.fetchall())
    if (f):
        mycursor.execute("drop DATABASE DUKH_Bank")
    mycursor.execute("CREATE DATABASE DUKH_BANK")  # CREATE THE DATABASE FOR DUKH_BANK
    mycursor.execute("USE DUKH_Bank")  # USE THE DATABASE FOR DUKH_BANK

    # CREATE THE TABLE FOR ACCOUNTS
    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`Accounts` (
      `Account Number` BIGINT(12) NOT NULL,
      `First Name` VARCHAR(45) NOT NULL,
      `Last Name` VARCHAR(45) NOT NULL,
      `Date Of Birth` DATE NOT NULL,
      `Phone Number` BIGINT(10) NOT NULL,
      `Email` VARCHAR(45) NOT NULL,
      `Gender` VARCHAR(45) NOT NULL,
      `Balance` FLOAT NOT NULL DEFAULT 0,
      `Account Type` VARCHAR(45) NOT NULL,
      `Address` VARCHAR(100) NOT NULL,
      PRIMARY KEY (`Account Number`),
      UNIQUE INDEX `Phone Number_UNIQUE` (`Phone Number` ASC) VISIBLE,
      UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE,
      INDEX `Balance_UNIQUE` (`Balance` DESC) VISIBLE,
      UNIQUE INDEX `Account Number_UNIQUE` (`Account Number` ASC) VISIBLE)""")

    # CREATE THE TABLE FOR EMPLOYEE
    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`Employee` (
      `EmpID` INT NOT NULL AUTO_INCREMENT,
      `First Name` VARCHAR(45) NOT NULL,
      `Last Name` VARCHAR(45) NOT NULL,
      `Date Of Birth` DATE NOT NULL,
      `Salary` FLOAT NOT NULL,
      `Designation` VARCHAR(45) NOT NULL,
      `Address` VARCHAR(180) NOT NULL,
      `Gender` VARCHAR(45) NOT NULL,
      `Phone Number` BIGINT(10) NOT NULL,
      `Email Address` VARCHAR(45) NOT NULL,
      PRIMARY KEY (`EmpID`),
      UNIQUE INDEX `EmpID_UNIQUE` (`EmpID` ASC) VISIBLE,
      UNIQUE INDEX `Phone Number_UNIQUE` (`Phone Number` ASC) VISIBLE,
      UNIQUE INDEX `Email Address_UNIQUE` (`Email Address` ASC) VISIBLE)""")

    # CREATE THE TABLE FOR BRANCHES
    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`Branches` (
      `Branch ID` INT NOT NULL AUTO_INCREMENT,
      `Name of Branch` VARCHAR(45) NOT NULL,
      `Address` VARCHAR(180) NOT NULL,
      `Staff Size` INT(4) NOT NULL,
      `Manager Emp ID` BIGINT(12) NOT NULL,
      `Date of Opening` DATE NOT NULL,
      PRIMARY KEY (`Branch ID`),
      UNIQUE INDEX `Branch ID_UNIQUE` (`Branch ID` ASC) VISIBLE,
      UNIQUE INDEX `Manager Emp ID_UNIQUE` (`Manager Emp ID` ASC) VISIBLE)""")

    # CREATE THE TABLE FOR FIXED DEPOSITS
    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`Fixed Deposits` (
      `FD Number` INT NOT NULL AUTO_INCREMENT,
      `Principal Amount` FLOAT NOT NULL,
      `Current Amount` FLOAT NOT NULL,
      `Interest Rate` INT(2) NOT NULL,
      `Date of Opening` DATE NOT NULL,
      `Maturation Period` VARCHAR(15) NOT NULL,
      PRIMARY KEY (`FD Number`))""")

    # CREATE THE TABLE FOR TRANSACTIONS
    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`Transactions` (
      `TransactionID` INT NOT NULL,
      `Amount` FLOAT NOT NULL,
      `Sender` BIGINT(12) NOT NULL,
      `Receiver` BIGINT(12) NOT NULL,
      `Mode Of Payment` VARCHAR(45) NOT NULL,
      `Timestamp` DATETIME(2) NOT NULL,
      PRIMARY KEY (`TransactionID`),
      INDEX `Amount_UNIQUE` (`Amount` DESC) VISIBLE,
      UNIQUE INDEX `TransactionID_UNIQUE` (`TransactionID` ASC) VISIBLE)""")

    # CREATE THE TABLE FOR DEBIT CARDS
    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`Debit Card` (
      `Card Number` BIGINT(16) NOT NULL,
      `Card Network` VARCHAR(45) NOT NULL,
      `Date of Issue` DATE NOT NULL,
      `Expiry Date` DATE AS (DATE_ADD(`date of issue`, INTERVAL 5 YEAR)),
      `CVV` INT(3) NOT NULL,
      `Account Balance` FLOAT NOT NULL DEFAULT 0,
      `Account Number` BIGINT(12) NOT NULL,
      PRIMARY KEY (`Card Number`),
      INDEX `Account Balance_UNIQUE` (`Account Balance` DESC) VISIBLE,
      UNIQUE INDEX `Card Number_UNIQUE` (`Card Number` ASC) VISIBLE)""")
    # CREATE THE INDEXES FOR TABLE FOR DEBIT CARDS
    mycursor.execute("""create index Account_number_index on `Debit Card`(`Account Number` ASC) """)

    # CREATE THE TABLE FOR CREDIT CARDS
    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`Credit Card` (
      `Card Number` BIGINT(16) NOT NULL,
      `Card Network` VARCHAR(45) NOT NULL,
      `Date of Issue` DATE NOT NULL,
      `Expiry Date` DATE AS (DATE_ADD(`date of issue`, INTERVAL 5 YEAR)),
      `CVV` INT(3) NOT NULL,  
      `Withdrawl Limit` FLOAT NOT NULL DEFAULT 0,
      `Account Number` BIGINT(12) NOT NULL,
      `Used Limit` FLOAT NOT NULL,
      PRIMARY KEY (`Card Number`),
      UNIQUE INDEX `Card Number_UNIQUE` (`Card Number` ASC) VISIBLE)""")
    # CREATE THE INDEXES FOR TABLE FOR CREDIT CARDS
    mycursor.execute("""create index Account_number_index on `Credit Card`(`Account Number` ASC) """)

    # CREATE THE TABLE FOR USER ACCOUNT LOGIN INFORMATION
    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`Customer Login Details` (
      `Account Number` BIGINT(12) NOT NULL,
      `Password Hash` VARCHAR(256) NOT NULL,
      PRIMARY KEY (`Account Number`),
      UNIQUE INDEX `Account Number_UNIQUE` (`Account Number` ASC) VISIBLE)""")
    # CREATE THE TABLE FOR EMPLOYEE ACCOUNT LOGIN INFORMATION
    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`Employee Login Details` (
          `EmpID` INT NOT NULL,
          `Password Hash` VARCHAR(256) NOT NULL,
          PRIMARY KEY (`EmpID`),
          UNIQUE INDEX `EmpID_UNIQUE` (`EmpID` ASC) VISIBLE)""")

    # CREATE THE TABLE FOR LOANS
    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`Loans` (
      `Amount` FLOAT NOT NULL,
      `Account Number` BIGINT(12) NOT NULL,
      `Date of Sanction` DATE NOT NULL,
      `Loan Type` VARCHAR(45) NOT NULL,
      `Interest Rate` FLOAT NOT NULL,
      `Next Due Amount` FLOAT NOT NULL,
      `Next Payment Date` DATE default (DATE_ADD(`date of Sanction`, INTERVAL 1 Month)),
      `Tenure` INT(2) NOT NULL,
      `Amount Paid` FLOAT NOT NULL,
      PRIMARY KEY (`Loan Type`,`Account Number`) )""")

    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`works in` (
  `Branches_Branch ID` INT NOT NULL,
  `Employee_EmpID` INT NOT NULL UNIQUE,
  `Date of Joining` DATE NOT NULL,
  PRIMARY KEY (`Employee_EmpID`))""")

    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`Creates FD` (
      `Account Number` BIGINT(12) NOT NULL,
      `FD Number` INT NOT NULL UNIQUE PRIMARY KEY )""")

    mycursor.execute("""CREATE TABLE IF NOT EXISTS `dukh_bank`.`manages` (
      `Account Number` BIGINT(12) NOT NULL UNIQUE PRIMARY KEY,
      `Employee_EmpID` INT NOT NULL) 
      """)

    mycursor.execute("CREATE USER IF NOT EXISTS 'user'@'localhost' IDENTIFIED BY 'user';")
    mycursor.execute("CREATE USER IF NOT EXISTS 'employee'@'localhost' IDENTIFIED BY 'employee';")
#     """GRANT SELECT ON Accounts to user@localhost;
# GRANT SELECT ON `Fixed Deposits` to user@localhost;
# GRANT SELECT ON `Transactions` to user@localhost;
# GRANT SELECT ON  `Debit Card` to user@localhost;
# GRANT SELECT ON `Credit Card` to user@localhost;
# GRANT SELECT ON `Loans` to user@localhost;
# GRANT SELECT ON `Creates FD` to user@localhost;
# GRANT SELECT ON `Customer Login Details` to user@localhost;
#
#
# GRANT INSERT ON `Fixed Deposits` to user@localhost;
# GRANT INSERT ON `Transactions` to user@localhost;
# GRANT INSERT ON `Loans` to user@localhost;
# GRANT INSERT ON `Creates FD` to user@localhost;
#
# GRANT UPDATE ON Accounts to user@localhost;
# GRANT UPDATE ON `Fixed Deposits` to user@localhost;
# GRANT UPDATE ON `Transactions` to user@localhost;
# GRANT UPDATE ON  `Debit Card` to user@localhost;
# GRANT UPDATE ON `Credit Card` to user@localhost;
# GRANT UPDATE ON `Loans` to user@localhost;
# GRANT UPDATE ON `Creates FD` to user@localhost;
#
#
# GRANT SELECT ON Accounts to employee@localhost;
# GRANT SELECT ON `Fixed Deposits` to employee@localhost;
# GRANT SELECT ON  `Debit Card` to employee@localhost;
# GRANT SELECT ON `Credit Card` to employee@localhost;
# GRANT SELECT ON `Loans` to employee@localhost;
# GRANT SELECT ON `Creates FD` to employee@localhost;
# GRANT SELECT ON `Employee Login Details` to employee@localhost;
#
#
# GRANT INSERT ON `Loans` to employee@localhost
# GRANT INSERT ON `Accounts` to employee@localhost
# GRANT INSERT ON `Credit card` to employee@localhost
# GRANT INSERT ON `Debit card` to employee@localhost
#
# GRANT UPDATE ON Accounts to employee@localhost
# GRANT UPDATE ON  `Debit Card` to employee@localhost
# GRANT UPDATE ON `Credit Card` to employee@localhost
# GRANT UPDATE ON `Loans` to employee@localhost
#
#
# """

    mycursor.execute("""    
        create trigger Deactivate_Credit_Card
        before delete
        on `Credit Card` for each row
        begin
        Update Accounts Set balance=balance-OLD.`Used Limit` where `Account Number`=OLD.`Account Number`;
        end
        """)

    mycursor.execute("""
        create trigger Break_FD
        before delete
        on `Fixed Deposits` for each row
        begin
          declare acc_no bigint(12);
          declare b float;

          select `Account Number` into acc_no from `Creates FD` where `FD Number`=OLD.`FD Number`;
          delete from `Creates FD` where `FD Number`=OLD.`FD Number`;
          select `Current Amount` into b from `Fixed Deposits` where `FD Number`=OLD.`FD Number`;

          Update Accounts Set balance=balance+b where `Account Number`=acc_no;

        end
        """)

    mycursor.execute("""create trigger Handle_Loan
        before Update 
        on `Loans` for each row
        begin
          if(new.`Next Due Amount`=0) then  
            set new.`Next Payment Date` = DATE_ADD(old.`Next Payment Date`, INTERVAL 1 month), new.`Next Due Amount`=new.`Amount`/new.tenure;
          end if;
        end""")

    # mycursor.execute("""
    #     create trigger Delete_Account
    #     before delete
    #     on `Accounts` for each row
    #     begin
    #       Delete from `Fixed Deposits` where `FD Number` in (Select `FD Number` from `creates FD` where `Account Number`=OLD.`Account Number`);
    #       Delete from `creates fd` where `Account Number`=OLD.`Account Number`;
    #       Delete from `Transactions` where Sender=OLD.`Account Number` OR receiver=OLD.`Account Number`;
    #       Delete from `Debit Card` where `Account Number`=OLD.`Account Number`;
    #       Delete from `Credit Card` where `Account Number`=OLD.`Account Number`;
    #       Delete from `Customer Login Details` where `Account Number`=OLD.`Account Number`;
    #       Delete from `Loans` where `Account Number`=OLD.`Account Number`;
    #       Delete from `Manages` where `Account Number`=OLD.`Account Number`;
    #
    #     end
    #     """)



fake = Faker()
addresslist = []

for i in range(500):
    str1 = fake.address()
    newstr = str1.replace("\n", " ")
    addresslist.append(newstr)

emaillist = ["gmail", "yahoo", "outlook", "hotmail"]
Account_No = 100000000000
visited_phone = []
fd_no = 0


def generate_person(mycursor, total_accounts):
    global Account_No
    global visited_phone

    gender = random.choice(["male", "female"])
    name = names.get_full_name(gender).split()
    firstname = name[0]
    lastname = name[1]
    address = addresslist[random.randint(0, len(addresslist) - 1)]
    email = firstname + lastname + str(random.randint(0, 100)) + "@" + emaillist[
        random.randint(0, len(emaillist) - 1)] + random.choice([".in", '.com'])
    phone_number = random.randint(9000000000, 9999999999)
    while (phone_number in visited_phone):
        phone_number = random.randint(9000000000, 9999999999)
    visited_phone.append(phone_number)
    AccountType = random.choice(["Savings", "Current"])
    Balance = random.randint(0, 10000000)
    year = random.randint(1960, 2010)
    DOB = str(year) + "-" + str(random.randint(1, 12)) + "-" + str(random.randint(1, 28))
    person = [Account_No, firstname, lastname, DOB, phone_number, email, gender, Balance, AccountType, address]
    Account_No += 1
    sql = "INSERT INTO Accounts VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
    mycursor.execute(sql, person)

    # ACCOUNTS POPULATED
    year_of_openingaccount = random.randint(year, year + 10)
    date_of_opening_ofaccount = str(year_of_openingaccount) + "-" + str(random.randint(1, 12)) + "-" + str(
        random.randint(1, 28))

    # FD POPULATION
    global fd_no
    no_of_fds = random.randint(0, 5)
    for itemp in range(no_of_fds):
        fd_no += 1
        interest_rate = random.randint(6, 12)
        principal_amount = random.randint(10000, 10000000)
        date_of_opening = str(random.randint(year_of_openingaccount, year_of_openingaccount + 10)) + "-" + str(
            random.randint(1, 12)) + "-" + str(random.randint(1, 28))
        maturation_period = random.choice(
            [str(random.randint(1, 12)) + " months", str(random.randint(1, 10)) + " years"])
        if (maturation_period.split()[1] == "months"):
            current_amount = principal_amount + interest_rate * principal_amount * int(
                maturation_period.split()[0]) / 1200
        elif (maturation_period.split()[1] == "years"):
            current_amount = (principal_amount + interest_rate * principal_amount * int(
                maturation_period.split()[0])) / 100
        fd = [fd_no, principal_amount, current_amount, interest_rate, date_of_opening, maturation_period]
        sql = "INSERT INTO `fixed deposits` VALUES (%s, %s, %s, %s, %s, %s)"
        mycursor.execute(sql, fd)
        sql = "INSERT INTO `Creates FD` VALUES (%s, %s)"
        fd2 = [Account_No, fd_no]
        mycursor.execute(sql, fd2)

    if (random.choice([True, False])):  # CREDIT CARD
        cardnumber = int(
            str(random.randint(1000, 9999)) + str(random.randint(1000, 9999)) + str(random.randint(1000, 9999)) + str(
                random.randint(1000, 9999)))
        cvv = random.randint(100, 999)
        yearofissue = random.randint(0, 10) + year_of_openingaccount
        dateofcardissue = str(yearofissue) + "-" + str(random.randint(1, 12)) + "-" + str(random.randint(1, 28))
        # yearofexpiry = random.randint(2, 5) + yearofissue
        # dateofcardexpiry = str(yearofexpiry) + "-" + str(random.randint(1, 12)) + "-" + str(random.randint(1, 28))
        cardnetwork = random.choice(["Visa", "MasterCard", "American Express", "Discover", "RuPay"])
        withdrawllimit = random.randint(1, 5) * 100000
        usedlimit = withdrawllimit * random.randint(10, 70) / 100
        credit = [cardnumber, cardnetwork, dateofcardissue, cvv, withdrawllimit, Account_No,
                  usedlimit]
        sql3 = "INSERT INTO `credit card`(`Card Number`,`Card Network`,`Date Of Issue`,`CVV`,`Withdrawl Limit`,`Account Number`, `Used Limit`) VALUES (%s, %s, %s, %s, %s,%s,%s)"
        mycursor.execute(sql3, credit)

    if (random.choice([True, False])):  # DEBIT CARD
        cardnumber = int(
            str(random.randint(1000, 9999)) + str(random.randint(1000, 9999)) + str(random.randint(1000, 9999)) + str(
                random.randint(1000, 9999)))
        cvv = random.randint(100, 999)
        yearofissue = random.randint(0, 10) + year_of_openingaccount
        dateofcardissue = str(yearofissue) + "-" + str(random.randint(1, 12)) + "-" + str(random.randint(1, 28))
        yearofexpiry = random.randint(2, 5) + yearofissue
        dateofcardexpiry = str(yearofexpiry) + "-" + str(random.randint(1, 12)) + "-" + str(random.randint(1, 28))
        cardnetwork = random.choice(["Visa", "MasterCard", "American Express", "Discover", "RuPay"])
        account_balance = Balance
        debit = [cardnumber, cardnetwork, dateofcardissue, cvv, account_balance, Account_No]
        sql4 = "INSERT INTO `debit card`(`Card Number`,`Card Network`,`Date Of Issue`,`CVV`,`Account Balance`,`Account Number`) VALUES (%s, %s, %s, %s, %s,%s)"
        mycursor.execute(sql4, debit)

    # TRANSACTIONS
    for tt in range(random.randint(20, 50)):
        transaction_id = random.randint(10000000, 99999999)
        modeofpayment = random.choice(
            ["Credit Card", "Debit Card", "Net Banking", "Cheque", "Demand Draft", "NEFT", "RTGS", "UPI"])
        amount = random.randint(100, Balance)
        sender = Account_No
        receiver = random.randint(100000000000, 100000000000 + total_accounts)
        while (receiver == sender):
            receiver = random.randint(100000000000, 100000000000 + total_accounts)
        transyear = 2020 + random.randint(0, 10)
        transaction_datetime = str(year) + "-" + str(random.randint(1, 12)) + "-" + str(
            random.randint(1, 28)) + " " + str(random.randint(0, 23)) + ":" + str(random.randint(0, 59)) + ":" + str(
            random.randint(0, 59))
        transaction = [transaction_id, amount, sender, receiver, modeofpayment, transaction_datetime]
        sql5 = "INSERT INTO `transactions` VALUES (%s, %s, %s, %s, %s, %s)"
        mycursor.execute(sql5, transaction)

    # login info
    N = random.randint(8, 32)
    # password = ''.join(random.choices(string.ascii_uppercase +string.digits, k=N))
    password = str(Account_No % 10)
    password = bytes(password, 'utf-8')
    pass_hash = hashlib.sha256(password).hexdigest()
    account_login = [Account_No, pass_hash]
    sql6 = "INSERT INTO `Customer Login Details` VALUES (%s, %s)"
    mycursor.execute(sql6, account_login)

    # Loans:
    temp = []
    for xx in range(random.randint(0, 4)):
        loan_type = random.choice(["education", "personal", "car", "home", "business"])
        if loan_type not in temp:
            temp.append(loan_type)
            amount_loan = random.randint(10, 50) * 100000
            interest_rate_ofloan = random.randint(6, 12)
            tenure = random.randint(1, 10)
            next_due_amount = amount_loan / tenure
            year_of_sanction = random.randint(year_of_openingaccount, year_of_openingaccount + 10)
            date_of_sanction = str(year_of_sanction) + "-" + str(random.randint(1, 12)) + "-" + str(
                random.randint(1, 28))
            year_of_next_payment = random.randint(year_of_sanction, year_of_sanction + tenure - 1)
            next_payment_date = str(year_of_next_payment) + "-" + str(random.randint(1, 12)) + "-" + str(
                random.randint(1, 28))
            amount_paid = (year_of_next_payment - year_of_sanction) * amount_loan / tenure

            loan = [amount_loan, Account_No, date_of_sanction, loan_type, interest_rate_ofloan, next_due_amount,
                    next_payment_date, tenure, amount_paid]
            sql6 = "INSERT INTO `Loans` VALUES (%s, %s, %s, %s, %s, %s,%s,%s,%s)"
            mycursor.execute(sql6, loan)


branches = []
branchID = 0
empID = 0


def generate_branch(mycursor, total_accounts):
    global branchID
    global empID
    global visited_phone
    empID += 1
    branchID += 1
    branch_address = addresslist[random.randint(0, len(addresslist) - 1)]
    year = random.randint(1957, 1960)
    Branch_opening = str(year) + "-" + str(random.randint(1, 12)) + "-" + str(random.randint(1, 28))
    branch_name = branch_address.split()[-3][:-1]
    while (branch_name in branches):
        branch_address = addresslist[random.randint(0, len(addresslist) - 1)]
        branch_name = branch_address.split()[-3][:-1]
    branches.append(branch_name)
    gender = random.choice(["male", "female"])
    name = names.get_full_name(gender).split()
    firstname = name[0]
    lastname = name[1]
    year = random.randint(1960, 2010)
    DOB = str(year) + "-" + str(random.randint(1, 12)) + "-" + str(random.randint(1, 28))
    address = addresslist[random.randint(0, len(addresslist) - 1)]
    email = firstname + lastname + str(random.randint(0, 100)) + "@" + emaillist[
        random.randint(0, len(emaillist) - 1)] + random.choice([".in", '.com'])
    phone_number = random.randint(9000000000, 9999999999)
    while (phone_number in visited_phone):
        phone_number = random.randint(9000000000, 9999999999)
    visited_phone.append(phone_number)
    Designation = "Manager"
    Salary = 150000
    manager = [empID, firstname, lastname, DOB, Salary, Designation, address, gender, phone_number, email]
    sql = "INSERT INTO `employee` VALUES (%s, %s, %s, %s, %s, %s ,%s,%s, %s, %s)"
    mycursor.execute(sql, manager)
    staff_size = random.randint(5, 20)
    branch = [branchID, branch_name, branch_address, staff_size, empID, Branch_opening]
    sql = "INSERT INTO `branches` VALUES (%s, %s, %s, %s, %s, %s )"
    mycursor.execute(sql, branch)
    for kk in range(staff_size - 1):
        N = random.randint(8, 32)
        passw = ''.join(random.choices(string.ascii_uppercase + string.digits, k=N))
        passw = bytes(passw, 'utf-8')
        generate_employee(mycursor, branchID, passw, total_accounts)


account_no_for_opening = 100000000000


def generate_employee(mycursor, branchID, password, total_accounts):
    global visited_phone
    global empID
    global account_no_for_opening
    empID += 1
    gender = random.choice(["male", "female"])
    name = names.get_full_name(gender).split()
    firstname = name[0]
    lastname = name[1]
    address = addresslist[random.randint(0, len(addresslist) - 1)]
    email = firstname + lastname + str(random.randint(0, 100)) + "@" + emaillist[
        random.randint(0, len(emaillist) - 1)] + random.choice([".in", '.com'])
    phone_number = random.randint(9000000000, 9999999999)
    while (phone_number in visited_phone):
        phone_number = random.randint(9000000000, 9999999999)
    visited_phone.append(phone_number)
    Designation = random.choice(["Cashier", "Clerk", "Loan Officer", "Sales Officer"])
    Salary = random.randint(80000, 120000)
    year = random.randint(1960, 2010)
    DOB = str(year) + "-" + str(random.randint(1, 12)) + "-" + str(random.randint(1, 28))
    employee = [empID, firstname, lastname, DOB, Salary, Designation, address, gender, phone_number, email]
    sql = "INSERT INTO `employee` VALUES (%s, %s, %s, %s, %s, %s ,%s,%s, %s, %s)"
    mycursor.execute(sql, employee)
    date_of_joining = str(year + random.randint(20, 30)) + "-" + str(random.randint(1, 12)) + "-" + str(
        random.randint(1, 28))
    worksin = [branchID, empID, date_of_joining]
    sql2 = "INSERT INTO `works in` VALUES (%s, %s, %s)"
    mycursor.execute(sql2, worksin)
    pass_hash = hashlib.sha256(password).hexdigest()
    account_login = [empID, pass_hash]
    sql6 = "INSERT INTO `Employee Login Details` VALUES (%s, %s)"
    mycursor.execute(sql6, account_login)

    for jj in range(random.randint(0, 3)):

        account_no_for_opening += 1
        if (account_no_for_opening < 100000000000 + total_accounts):
            entry = [account_no_for_opening, empID]
            sql8 = "INSERT INTO `manages` VALUES (%s, %s )"
            mycursor.execute(sql8, entry)


mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    passwd="root"
)

mycursor = mydb.cursor()
initialize_bank(mycursor)

x = mycursor.execute("show tables", multi=True)
for i in x:
    print(i.fetchall())

for i in range(100):
    generate_person(mycursor, 100)
for i in range(10):
    generate_branch(mycursor, 100)

# x = mycursor.execute("select * from accounts", multi=True)
# for i in x:
#     for j in i.fetchall():
#         print(j)
# x=mycursor.execute("select * from `fixed deposits`",multi=True)
# for i in x:
#     for j in i.fetchall():
#         print(j)
# x=mycursor.execute("select * from `creates FD`",multi=True)
# for i in x:
#     for j in i.fetchall():
#         print(j)
# x=mycursor.execute("select * from `employee`",multi=True)
# for i in x:
#     for j in i.fetchall():
#         print(j)
# x=mycursor.execute("select * from `works in`",multi=True)
# for i in x:
#     for j in i.fetchall():
#         print(j)
# x = mycursor.execute("select * from `transactions`", multi=True)
# for i in x:
#     for j in i.fetchall():
#         print(j)
# x = mycursor.execute("select * from `debit card`", multi=True)
# for i in x:
#     for j in i.fetchall():
#         print(j)
# x = mycursor.execute("select * from `manages`", multi=True)
# for i in x:
#     for j in i.fetchall():
#         print(j)
# x = mycursor.execute('''SELECT `First name`,email FROM Accounts Where `account number` in (select `account number` from `credit card` where use limit>=0.75*`withdrawal limit`)
#  ''', multi=True)
# for i in x:
#     for j in i.fetchall():
#         print(j)
mydb.commit()

