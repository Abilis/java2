-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Апр 15 2016 г., 18:55
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
CREATE DATABASE IF NOT EXISTS `finmanager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `finmanager`;

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
(1, 'first acc for test', 4000, 9),
(2, 'second account for test', 5000, 9),
(3, 'third account for user test', 499, 8),
(4, 'fourth account', 2999, 9),
(13, 'Первый аккаунт', 9000, 10),
(14, 'Второй аккаунт', 400, 10),
(15, 'Третий аккаунт', 45454, 10),
(16, 'Четвертый аккаун', 4544, 10);

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
(17, 0, '2016-04-14 12:00:47', 333, 'Куплена мороженка', 'Развлечения', 1),
(18, 0, '2016-04-14 12:28:50', 100, 'something', 'category', 1),
(19, 0, '2016-04-14 12:30:21', 1100, 'Велонасос', 'Развлечения', 1),
(20, 0, '2016-04-14 12:43:42', 900, 'Футбольный мят', 'Спорт', 1),
(21, 0, '2016-04-14 01:47:40', 200, 'кутеж', 'здоровье', 13),
(22, 1, '2016-04-14 01:49:46', 10000, 'Приход', 'Развлечения', 13),
(23, 1, '2016-04-15 08:18:59', 3000, 'Дебет', 'Тестовая категория', 1),
(24, 1, '2016-04-15 08:34:22', 3000, 'Смотрим, как пополняется остаток на счете', 'тестовая категория', 1),
(25, 0, '2016-04-15 08:36:01', 3211, 'На кутеж', 'кутеж', 1),
(26, 1, '2016-04-15 08:36:31', 1000, 'Добавление 1000', 'пополнение', 1),
(27, 1, '2016-04-15 08:38:36', 1000, 'еще пополнение на 1000', 'тесты', 1),
(28, 0, '2016-04-15 08:39:08', 500, 'кино, вино и домино', 'стиль жизни', 1),
(29, 0, '2016-04-15 08:47:45', 500, 'Еще какая-то тестовая запись', 'Какая-то категория', 1),
(30, 0, '2016-04-15 08:52:34', 1000, 'Тестовое снятие', 'тестовая категория', 13);

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
(9, 'user', '1a1dc91c907325c69271ddf0c944bc72'),
(10, 'test2', 'c1572d05424d0ecb2a65ec6a82aeacbf');

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
  MODIFY `id_acc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT для таблицы `records`
--
ALTER TABLE `records`
  MODIFY `id_rec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
