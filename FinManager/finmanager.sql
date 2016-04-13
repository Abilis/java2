-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Апр 13 2016 г., 15:54
-- Версия сервера: 10.1.9-MariaDB
-- Версия PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `finmanager`
--

-- --------------------------------------------------------

--
-- Структура таблицы `accounts`
--

CREATE TABLE `accounts` (
  `id_acc` int(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  `ostatok` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `accounts`
--

INSERT INTO `accounts` (`id_acc`, `description`, `ostatok`, `id_user`) VALUES
(1, 'first acc for test', 1000, 9),
(2, 'second account for test', 5000, 9),
(3, 'third account for user test', 499, 8),
(4, 'fourth account', 2999, 9);

-- --------------------------------------------------------

--
-- Структура таблицы `records`
--

CREATE TABLE `records` (
  `id_rec` int(11) NOT NULL,
  `label` tinyint(1) NOT NULL,
  `dt` datetime NOT NULL,
  `sum` int(11) NOT NULL,
  `description` varchar(150) NOT NULL,
  `category` varchar(100) NOT NULL,
  `id_acc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `records`
--

INSERT INTO `records` (`id_rec`, `label`, `dt`, `sum`, `description`, `category`, `id_acc`) VALUES
(1, 1, '2016-04-05 00:00:00', 400, 'тестовое описание', 'тестовая категория', 1),
(2, 1, '2016-01-15 00:00:00', 455, 'Второе тестовое описание', 'Вторая тестовая категория', 1),
(3, 0, '2016-04-22 00:00:00', 777, 'Третье тестовое описание', 'Третья тестовая категория', 2),
(4, 0, '2016-01-12 05:26:27', 255, 'четвертое тестовое описание', 'четвертая тестовая категория', 1),
(11, 1, '2016-04-13 01:23:54', 999, 'Вставленное описание', 'Вставленная категория', 1),
(12, 1, '2016-04-13 01:24:01', 999, 'Вставленное описание', 'Вставленная категория', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `login` varchar(25) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id_user`, `login`, `password`) VALUES
(8, 'test', '098f6bcd4621d373cade4e832627b4f6'),
(9, 'user', '1a1dc91c907325c69271ddf0c944bc72');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id_acc`);

--
-- Индексы таблицы `records`
--
ALTER TABLE `records`
  ADD PRIMARY KEY (`id_rec`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id_acc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT для таблицы `records`
--
ALTER TABLE `records`
  MODIFY `id_rec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
