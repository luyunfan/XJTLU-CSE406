-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2019-03-11 13:14:25
-- 服务器版本： 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- 表的结构 `book`
--

CREATE TABLE `book`
(
  `call_no` int(11) NOT NULL,
  `title`   char(20) DEFAULT NULL,
  `subject` char(20) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `book`
--

INSERT INTO `book` (`call_no`, `title`, `subject`)
VALUES (100, 'Physics Handbook', 'Physics'),
       (200, 'Database Systems', 'Computing'),
       (300, 'Modula-2', 'Computing'),
       (400, 'Database Design', 'Computing'),
       (500, 'Software Testing', 'Computing'),
       (600, 'Business Society', 'Business'),
       (700, 'Graphs', 'Mathematics'),
       (800, 'Cell Biology', 'Biology'),
       (900, 'Set Theory', 'Mathematics');

-- --------------------------------------------------------

--
-- 表的结构 `book1`
--

CREATE TABLE `book1`
(
  `Book_no` int(11)     NOT NULL,
  `Title`   varchar(20) NOT NULL,
  `Subject` char(12) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `book1`
--

INSERT INTO `book1` (`Book_no`, `Title`, `Subject`)
VALUES (100, 'Physics Handbook', 'Physics'),
       (101, 'Physics Handbook2', 'Physics'),
       (102, 'Physics Handbook3', 'Physics'),
       (103, 'English Handbook', 'English'),
       (104, 'English Handbook2', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `borrower`
--

CREATE TABLE `borrower`
(
  `user_id` int(11) NOT NULL,
  `name`    varchar(100) DEFAULT NULL,
  `age`     int(11)      DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `borrower`
--

INSERT INTO `borrower` (`user_id`, `name`, `age`)
VALUES (100, 'Wong', 22),
       (150, 'Colin', 31),
       (200, 'King', 21),
       (250, 'Das', 67),
       (300, 'Niall', 17),
       (350, 'Smith', 72),
       (400, 'Jones', 41);

-- --------------------------------------------------------

--
-- 表的结构 `date_test`
--

CREATE TABLE `date_test`
(
  `id`    int(11) NOT NULL,
  `birth` date DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `date_test`
--

INSERT INTO `date_test` (`id`, `birth`)
VALUES (1, '1999-01-01'),
       (2, '1988-09-10'),
       (3, '2000-10-17');

-- --------------------------------------------------------

--
-- 表的结构 `grade`
--

CREATE TABLE `grade`
(
  `id`    int(11) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `class` int(11) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `grade`
--

INSERT INTO `grade` (`id`, `score`, `class`)
VALUES (1, 24, 2),
       (2, 40, 1),
       (3, 48, 2),
       (4, 70, 2),
       (5, 100, 1);

-- --------------------------------------------------------

--
-- 表的结构 `grade_test`
--

CREATE TABLE `grade_test`
(
  `id`    int(11) NOT NULL,
  `grade` int(11) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `grade_test`
--

INSERT INTO `grade_test` (`id`, `grade`)
VALUES (1, 24),
       (2, 36),
       (3, 42),
       (4, 84),
       (5, 100);

-- --------------------------------------------------------

--
-- 表的结构 `hl`
--

CREATE TABLE `hl`
(
  `book_no` int(11) NOT NULL,
  `level`   int(11) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `hl`
--

INSERT INTO `hl` (`book_no`, `level`)
VALUES (101, 3),
       (102, 1),
       (103, 2),
       (104, 4),
       (105, 5);

-- --------------------------------------------------------

--
-- 表的结构 `jiang`
--

CREATE TABLE `jiang`
(
  `Book_no` int(11)     NOT NULL,
  `Title`   varchar(20) NOT NULL,
  `Subject` char(12) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- 表的结构 `library`
--

CREATE TABLE `library`
(
  `book_no` int(11) NOT NULL,
  `price`   int(11) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `library`
--

INSERT INTO `library` (`book_no`, `price`)
VALUES (101, 24),
       (102, 48),
       (103, 240),
       (104, 145),
       (105, 6);

-- --------------------------------------------------------

--
-- 表的结构 `loan`
--

CREATE TABLE `loan`
(
  `call_no`  int(11) NOT NULL,
  `user_id`  int(11)        DEFAULT NULL,
  `date_due` date           DEFAULT NULL,
  `date_ret` date           DEFAULT NULL,
  `fine`     decimal(10, 2) DEFAULT NULL,
  `paid`     tinyint(1)     DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `loan`
--

INSERT INTO `loan` (`call_no`, `user_id`, `date_due`, `date_ret`, `fine`, `paid`)
VALUES (100, 100, '1988-09-12', '1988-09-01', '0.00', 0),
       (300, 100, '1988-09-01', NULL, '0.00', 0),
       (900, 200, '1988-01-01', '1988-12-20', '1.90', 1),
       (400, 200, '1989-12-04', '1990-05-16', '16.30', 1),
       (600, 200, '1989-12-04', '1990-05-16', '16.30', 1),
       (500, 250, '1984-10-02', NULL, '0.00', 0),
       (600, 250, '1984-10-02', '1985-10-02', '36.50', 1),
       (700, 300, '1988-12-10', '1988-12-01', '0.00', 0),
       (800, 350, '1988-10-01', '1988-12-30', '2.90', 1),
       (900, 400, '1990-10-10', NULL, '0.00', 0);

-- --------------------------------------------------------

--
-- 表的结构 `mark`
--

CREATE TABLE `mark`
(
  `id`      int(11) NOT NULL,
  `Math`    int(11) DEFAULT NULL,
  `Chinese` int(11) DEFAULT NULL,
  `English` int(11) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `mark`
--

INSERT INTO `mark` (`id`, `Math`, `Chinese`, `English`)
VALUES (1, 100, 100, 95),
       (2, 10, 10, 100),
       (3, 100, 85, 60),
       (4, 80, 100, 100),
       (5, 85, 85, 95);

-- --------------------------------------------------------

--
-- 表的结构 `mark_info`
--

CREATE TABLE `mark_info`
(
  `id`    int(11) NOT NULL,
  `score` int(11) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `mark_info`
--

INSERT INTO `mark_info` (`id`, `score`)
VALUES (1, 70),
       (2, 100),
       (4, 40);

-- --------------------------------------------------------

--
-- 表的结构 `people`
--

CREATE TABLE `people`
(
  `user_id` int(11) NOT NULL,
  `name`    varchar(20) DEFAULT NULL,
  `salary`  int(11)     DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `people`
--

INSERT INTO `people` (`user_id`, `name`, `salary`)
VALUES (1001, 'Alic', 1200),
       (1002, 'John', 2400),
       (1003, 'Eric', 5000),
       (1004, 'Reco', 10000),
       (1005, 'Wong', 30000);

-- --------------------------------------------------------

--
-- 表的结构 `price`
--

CREATE TABLE `price`
(
  `Book_no` int(11) NOT NULL,
  `Price`   int(11) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `price`
--

INSERT INTO `price` (`Book_no`, `Price`)
VALUES (100, 60),
       (101, 45),
       (102, 120),
       (103, 200);

-- --------------------------------------------------------

--
-- 表的结构 `stu_info`
--

CREATE TABLE `stu_info`
(
  `id`   int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `stu_info`
--

INSERT INTO `stu_info` (`id`, `name`)
VALUES (1, 'Reco'),
       (2, 'James'),
       (3, 'Eric');

-- --------------------------------------------------------

--
-- 表的结构 `tt`
--

CREATE TABLE `tt`
(
  `tt`      int(11)     NOT NULL,
  `ttt`     char(30)    NOT NULL,
  `tttt`    varchar(40) NOT NULL,
  `subject` int(11)     NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- 转存表中的数据 `tt`
--

INSERT INTO `tt` (`tt`, `ttt`, `tttt`, `subject`)
VALUES (10, 'rr', 'rrr', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`call_no`);

--
-- Indexes for table `book1`
--
ALTER TABLE `book1`
  ADD PRIMARY KEY (`Book_no`);

--
-- Indexes for table `borrower`
--
ALTER TABLE `borrower`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `date_test`
--
ALTER TABLE `date_test`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `grade_test`
--
ALTER TABLE `grade_test`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hl`
--
ALTER TABLE `hl`
  ADD PRIMARY KEY (`book_no`);

--
-- Indexes for table `jiang`
--
ALTER TABLE `jiang`
  ADD PRIMARY KEY (`Book_no`);

--
-- Indexes for table `library`
--
ALTER TABLE `library`
  ADD PRIMARY KEY (`book_no`);

--
-- Indexes for table `mark`
--
ALTER TABLE `mark`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mark_info`
--
ALTER TABLE `mark_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `people`
--
ALTER TABLE `people`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `price`
--
ALTER TABLE `price`
  ADD PRIMARY KEY (`Book_no`);

--
-- Indexes for table `stu_info`
--
ALTER TABLE `stu_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tt`
--
ALTER TABLE `tt`
  ADD PRIMARY KEY (`tt`);
COMMIT;

CREATE TABLE t_student
(
  student_id      INT         NOT NULL,
  student_name    VARCHAR(20) NULL,
  student_country VARCHAR(20) NULL,
  CONSTRAINT test_student_id_uindex
    UNIQUE (student_id)
);

ALTER TABLE t_student
  ADD PRIMARY KEY (student_id);


INSERT INTO t_student
VALUES (1, 'Yunfan', 'China');
INSERT INTO t_student
VALUES (2, 'Hanning', 'China');

/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
