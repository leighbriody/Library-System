-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2021 at 05:47 PM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarydb_test_2`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `address_id` int(11) NOT NULL,
  `address_line_1` varchar(50) NOT NULL,
  `address_line_2` varchar(50) DEFAULT NULL,
  `county` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `country` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`address_id`, `address_line_1`, `address_line_2`, `county`, `city`, `country`) VALUES
(1, '2 Seaford Gardens', 'The village', 'Louth', 'Dundalk', 'Ireland'),
(2, '3 Bellview Gardens', 'Dublin Road', 'Meath', 'Drogheda', 'Ireland'),
(3, '4 Seaview road', 'The Little road', 'Longford', 'Navan', 'Ireland'),
(4, '5 Street view', 'The Main road', 'Cork', 'KInsale', 'Ireland'),
(5, '6 Castle Gardens', 'Turn Abbey', 'Kerry', 'Bannastrand', 'Ireland');

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE `authors` (
  `author_id` int(2) NOT NULL,
  `author_first_name` varchar(20) NOT NULL,
  `author_last_name` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`author_id`, `author_first_name`, `author_last_name`) VALUES
(2, 'Joseph', 'Blotner'),
(3, 'Deirdre', 'Blair'),
(4, 'Alice', 'Calaprice'),
(5, 'Trevor', 'Lipscombe'),
(6, 'Lew', 'Freedman'),
(7, 'Kristine', 'Larsen'),
(8, 'Michael', 'Meagher'),
(9, 'Larry', 'Gragg'),
(10, 'Arnold', 'Rampersad'),
(11, 'Hermione', 'Lee'),
(13, 'Ralph', 'Ketcham'),
(14, 'Marianne', 'Weber'),
(15, 'Ted', 'Bell'),
(16, 'Charles', 'Derry'),
(17, 'Natalie R', 'Collins'),
(18, 'Trsa', 'Sigurdardottir'),
(19, 'Ragnar', 'Jonasson'),
(20, 'Paul', 'Cleave'),
(21, 'Chad Michael', 'Murray'),
(22, 'Eli', 'Yance'),
(23, 'Brian', 'Freemantle'),
(24, 'VE', 'Schwab'),
(25, 'Jonathan', 'Allan'),
(26, 'Marian', 'Keyes'),
(27, 'Ogden', 'Whitney'),
(28, 'Sariah', 'Wilson'),
(29, 'Colleen ', 'Hoover'),
(30, 'Shirley', 'Jump'),
(31, 'Jen', 'DeLuca'),
(32, 'Geoff', 'King'),
(36, 'KJ', 'Dover'),
(38, 'Michael', 'Fontaine'),
(39, 'Adele', 'Scafuro'),
(40, 'Matthew', 'Bevis'),
(41, 'Andrew', 'Horton'),
(42, 'Joanna', 'Rapf'),
(43, 'Ian Christopher', 'Storey'),
(44, 'Jonathan', 'Gray'),
(45, 'Jeffrey', 'Jones'),
(46, 'Ethan', 'Thompson'),
(47, 'Carlo', 'Goldoni'),
(48, 'John', 'Jackman'),
(49, 'Wendy', 'Wren'),
(50, 'Geoff', 'Reilly'),
(51, 'Maureen', 'Lewis'),
(52, 'David', 'Wray'),
(54, 'Theodore', 'Cheney'),
(56, 'Pearson', 'Education'),
(57, 'Richard', 'Barsam'),
(58, 'Robert', 'McCrum'),
(59, 'Mattthew', 'Ricketson'),
(60, 'Heather', 'Graham'),
(62, 'Brad', 'Thor'),
(63, 'Walter', 'Scott'),
(64, 'Ezra', 'Pound'),
(65, 'Terrence', 'McNally'),
(66, 'Erik', 'Barnouw');

-- --------------------------------------------------------

--
-- Table structure for table `bookauthor`
--

CREATE TABLE `bookauthor` (
  `book_id` int(3) NOT NULL,
  `author_id` int(3) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookauthor`
--

INSERT INTO `bookauthor` (`book_id`, `author_id`, `status`) VALUES
(1, 2, 1),
(2, 3, 1),
(3, 6, 1),
(7, 8, 1),
(5, 10, 1),
(6, 11, 1),
(7, 13, 1),
(8, 14, 1),
(9, 15, 1),
(10, 16, 1),
(11, 17, 1),
(12, 18, 1),
(13, 19, 1),
(14, 20, 1),
(15, 60, 1),
(16, 22, 1),
(17, 23, 1),
(18, 24, 1),
(19, 44, 1),
(20, 26, 1),
(21, 27, 1),
(22, 28, 1),
(23, 29, 1),
(24, 29, 1),
(25, 31, 1),
(26, 32, 1),
(27, 36, 1),
(28, 38, 1),
(29, 40, 1),
(30, 59, 1),
(31, 43, 1),
(32, 47, 1),
(33, 54, 1),
(34, 56, 1),
(35, 57, 1),
(36, 58, 1),
(37, 59, 1),
(38, 4, 1),
(38, 5, 1),
(40, 8, 1),
(41, 9, 1),
(40, 60, 1),
(23, 21, 1),
(38, 39, 1),
(38, 42, 1),
(55, 65, 1),
(44, 48, 1),
(43, 45, 1),
(43, 46, 1),
(65, 66, 1),
(45, 48, 1),
(45, 49, 1),
(50, 51, 1),
(46, 49, 1),
(47, 50, 1),
(47, 49, 1),
(48, 48, 1),
(48, 49, 1),
(49, 52, 1),
(49, 51, 1);

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_id` int(7) NOT NULL,
  `book_name` varchar(100) NOT NULL,
  `genre` int(11) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `quantity` int(15) NOT NULL,
  `overdue_fee_per_day` decimal(3,2) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_id`, `book_name`, `genre`, `description`, `quantity`, `overdue_fee_per_day`, `status`) VALUES
(1, 'Faulkner: A Biography', 1, 'William Faulkner (1897-1962) remains the pre-eminent literary chronicler of the American South and a giant of American arts and letters. Creatively obsessed with problems of race, identity, power, politics, and family dynamics, he wrote novels, stories, and lectures that continue to shape our understanding of the region', 10, NULL, 0),
(2, 'Samuel Beckett: A Biography', 1, 'Samuel Beckett has become the standard work on the enigmatic, controversial, and Nobel Prize-winning creator of such contributions to 20th-century theater as Waiting for Godot and Endgame. 16 pages of black-and-white photographs.', 232, NULL, 1),
(3, 'LeBron James: A Biography', 1, 'Highlights the life and accomplishments of the high school basketball player, called \"The Chosen One\" by Sports Illustrated, who went on to become Rookie of the Year during his first NBA season.', 10, NULL, 1),
(4, 'Stephen Hawking: A Biography', 1, 'Presents the life and accomplishments of the English scientist, who, despite suffering from Lou Gehrigs disease, has become a renowned cosmologist whose theory of black holes has had a profound influence on the modern study of the universe …', 10, NULL, 1),
(5, 'Jackie Robinson: A Biography', 1, 'The extraordinary life of Jackie Robinson is illuminated as never before in this full-scale biography by Arnold Rampersad, who was chosen by Jacks widow, Rachel, to tell her husbands story, and was given unprecedented access to his …', 10, NULL, 1),
(6, 'Biography: A Very Short Introduction', 1, 'Biographies are one of the most popular and best-selling of the literary genres.', 10, NULL, 1),
(7, 'James Madison: A Biography', 1, 'Utilizing the vast amount of source material made available in the last 30 years, Professor Ketcham has captured the essential man in his times and in doing so has made him understandable for us in our own day.Los Angeles Times', 10, NULL, 1),
(8, 'Max Weber: A Biography', 1, 'Sociologist Robert A. Nisbet called it a moving and deeply felt biographical memoir. Historian Gerhard Masur cited the book as the foundation of all further inquiries into Max Webers life and influence.', 10, NULL, 1),
(9, 'Tsar: A Thriller', 2, 'Swashbuckling counter spy Alex Hawke is the only man, both Americans and the Brits agree, who can stop the madness created by the new Russia and its new Tsar, who is pulling strings and pulling them hard.', 10, NULL, 1),
(10, 'The Suspense Thriller: Films in the Shadow of Alfr...', 2, 'Thriller. Tippi Hedren as Mamie appears to be the model of decorum: her clothes are stylish, her hair swept back in a sophisticated manner, her features patrician. As a secretary, she is exemplary: responsible, proper, efficient. On this weekend …', 10, NULL, 1),
(11, 'Ties That Bind: A Thriller', 2, 'Police Detective Samantha Montgomery has seen her share of tragedy back in Salt Lake City—but this is different. This is methodical, planned, perfectly executed. This is the work of a serial killer.', 10, NULL, 1),
(12, 'The Legacy: A Thriller', 2, 'The Legacy is the first installment in a fantastic new series featuring the psychologist Freyja and the police officer Huldar.', 10, NULL, 1),
(13, 'The Mist: A Thriller', 2, 'The final nail-biting installment in Ragnar Jonassons critically-acclaimed Hidden Iceland series, The Mist, from the newest superstar on the Icelandic crime fiction scene. 1987.', 10, NULL, 1),
(14, 'A Killer Harvest: A Thriller', 2, '\"A blind teenager ... receives a corneal donation and begins to see and feel memories from their previous owner--a homicide detective who was also his father\"--Amazon.com.', 10, NULL, 1),
(15, 'American Drifter: A Thriller', 2, 'New York Times bestselling author Heather Graham has teamed up with celebrated actor and celebrity icon Chad Michael Murray to weave a tale of passion and danger in the captivating thriller suspense, American Drifter.', 10, NULL, 1),
(16, 'Fairwood: A Thriller', 2, 'This is crime-noir so dark that you wont see the twists that lurk in the shadows\" –Keith Moray, author of Inspector Torquil McKinnon Series Dexter and Pandora are stuck in the middle of nowhere, desperate to escape the clutches of the …', 10, NULL, 1),
(17, 'The Cloud Collector: A Thriller', 2, 'In The Cloud Collector, acclaimed spy novelist Brian Freemantle turns his experts eye to the frontlines of todays war on terror: the cryptic world of cyber warfare.', 10, NULL, 1),
(18, 'The Invisible Life of Addie LaRue', 3, 'In the vein of The Time Travelers Wife and Life After Life, The Invisible Life of Addie LaRue is New York Times bestselling author V. E. Schwabs genre-defying tour de force.', 10, NULL, 1),
(19, 'Men, Masculinities, and Popular Romance', 3, 'The romance novel has long been criticized and celebrated by feminist critics. How can these novels maintain, according to some, feminist ideals, while also upholding what Raewyn Connell has long theorized as \"hegemonic masculinity\"?', 10, NULL, 1),
(20, 'Rachels Holiday', 3, 'People stared at me as I laughed to myself C L Moore A born storyteller Independent on Sunday The voice of a generation Daily Mirror Praise for Marian Keyes: Comic, convincing and true Guardian Mercilessly funny The Times Funny, …', 10, NULL, 1),
(21, 'Return to Romance: The Strange Love Stories of Ogd...', 3, 'By turns amusing and disturbing, this collection of 1960s romance comic strips provides a provocative window into male-female power dynamics as conceived by one of mid-century Americas foremost comic book artists.', 10, NULL, 1),
(22, 'Roommaid', 3, 'From bestselling author Sariah Wilson comes a charming romance about living your life one dream at a time.', 10, NULL, 1),
(23, 'It Ends with Us: A Novel', 3, 'An honest, evocative, and tender novel, It Ends with Us is “a glorious and touching read, a forever keeper. The kind of book that gets handed down” (USA TODAY).', 10, NULL, 1),
(24, 'The Roommate: the perfect feel-good sexy romcom fo.', 3, 'An honest, evocative, and tender novel, It Ends with Us is “a glorious and touching read, a forever keeper. The kind of book that gets handed down” (USA TODAY).', 10, NULL, 1),
(25, 'Well Played', 3, 'A laugh-out-loud romantic comedy featuring kilted musicians, Renaissance Faire tavern wenches, and an unlikely love story.', 10, NULL, 1),
(26, 'Film Comedy', 4, 'Comedy is one of the most popular forms in film. But what exactly is film comedy and what might be the basis of its widespread appeal? This book takes a multi-perspective approach to answering these questions.', 10, NULL, 1),
(27, 'Aristophanic Comedy', 4, 'He succeeded in this effort by making people laugh, and the book pays more attention than has generally been paid to the technical means, whether of language or of situation, on which Aristophanes humor depends.', 10, NULL, 1),
(28, 'Funny Words in Plautine Comedy', 4, 'Combining textual and literary evidence, this book argues that many Plautine jokes, puns, and names of characters were misunderstood in antiquity.', 10, NULL, 1),
(29, 'Comedy: A Very Short Introduction', 4, 'With a broad scope across the millennia, from high literature to popular culture, between page and stage and screen, this Very Short Introduction considers comedy not only as a literary genre, but also as a broader impulse at work in many …', 10, NULL, 1),
(30, 'A Companion to Film Comedy', 4, 'With a broad scope across the millennia, from high literature to popular culture, between page and stage and screen, this Very Short Introduction considers comedy not only as a literary genre, but also as a broader impulse at work in many …', 10, NULL, 1),
(31, 'Eupolis, Poet of Old Comedy', 4, 'He wrote the same sort of topical and often indecent comedy as the surviving plays of Aristophanes. This book provides a translation of all the remaining fragments of his work and an essay on each lost play.', 10, NULL, 1),
(32, 'The Liar: A Comedy', 4, 'A Comedy - (12m, 4f) \"The story centers on Lelio, a young Venetian incapable of telling the truth.', 10, NULL, 1),
(33, 'Writing Creative Nonfiction: Fiction Techniques fo...', 5, 'This Invaluable Primer Shows Writers How To Apply Traditional Fiction-Writing Techniques To Make Their Non-Fiction Efforts More Vibrant, Powerful, And Engrossing.', 10, NULL, 1),
(34, 'Navigator Non-Fiction Year 3: Welcome to Planet Ea...', 5, 'Non-Fiction. Guided. Reading. and. Reading. Strategies. Modelling Modelling is a powerful teaching strategy. In guided ... reading. The skills and strategies used when reading non-fiction often differ from those used when reading fiction.', 10, NULL, 1),
(35, 'Nonfiction Film: A Critical History', 5, 'Richard Barsam has given us as comprehensive a study of the origins and development of the nonfiction mode in motion pictures as we are ever likely to have in one volume.', 10, NULL, 1),
(36, 'The 100 Best Nonfiction Books of All Time', 5, 'Beginning in 1611 with the King James Bible and ending in 2014 with Elizabeth Kolberts The Sixth Extinction, this extraordinary voyage through the written treasures of our culture examines universally-acclaimed classics such as Pepys', 10, NULL, 1),
(37, 'Telling True Stories: Navigating the challenges of...', 5, 'Explores the key challenges in writing narrative non-fiction, and shows how some of the best in the business do it - an invaluable guide for anyone who wants to tell true stories well.', 10, NULL, 1),
(38, 'Albert Einstein: A Biography', 1, 'Grounded in scholarship yet fired by the imagination, this book reveals the Singapore story to have been as rich, diverse and multilayered as the city-state is prosperous, ordered and successful today.', 10, NULL, 1),
(39, 'John F. Kennedy: A Biography: A Biography', 1, 'This biography examines the life and political career of a president whose idealism and policies continue to impact the world today despite his brief time in office.Includes rarely seen materials from Kennedys books, writings', 10, NULL, 1),
(40, 'American Drifter: A Thriller', 2, 'New York Times bestselling author Heather Graham has teamed up with celebrated actor and celebrity icon Chad Michael Murray to weave a tale of passion and danger in the captivating thriller suspense, American Drifter.', 10, NULL, 1),
(41, 'The Oxford Handbook of Greek and Roman Comedy', 4, 'The Oxford Handbook of Greek and Roman Comedy marks the first comprehensive introduction to and reference work for the unified study of ancient comedy.', 10, NULL, 1),
(42, 'Its Only a Play: A Comedy', 4, 'THE STORY: Its the opening night of The Golden Egg on Broadway, and the wealthy producer (Julia Budder) is throwing a lavish party in her lavish Manhattan townhouse.', 10, NULL, 1),
(43, 'Satire TV: Politics and Comedy in the Post-network...', 4, 'This work examines what happens when comedy becomes political, and politics become funny. A series of original essays focus on a range of programmes, from The Daily Show to South Park.', 10, NULL, 1),
(44, 'Documentary: A History of the Non-fiction Film', 5, 'Presents a history of the documentary film', 10, NULL, 1),
(45, 'Developing Non-Fiction Skills - Book 3', 5, 'The pupil books explore a wide range of texts using parallel themes.', 10, NULL, 1),
(46, 'Nelson Thornes Framework English Skills in Non-Fic...', 5, 'This pupils book is part of a Key Stage 3 English scheme that reflects National Literacy Strategy priorities by spanning both non-fiction and fiction text-types in twin student books.', 10, NULL, 1),
(47, 'Nelson Thornes Framework English Skills in Non-Fic...', 5, 'This book provides preparation for SATs in Year 9; prepares students for Key Stage 4 by raising standards of achievement; and supports other subjects across the curriculum in raising levels of literacy.', 10, NULL, 1),
(48, 'Developing Nonfiction Skills - Book 1', 5, 'The pupil books explore a wide range of texts using parallel themes.', 10, NULL, 1),
(49, 'Extending Literacy: Developing Approaches to Non-F...', 5, 'In this book David Wray and Maureen Lewis outline the thinking behind the project and describe in detail the many useful teaching strategies and approaches which were developed in collaboration with primary teachers across the country.', 10, NULL, 1),
(50, 'test book', 1, 'this testing is awful', 10, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cards`
--

CREATE TABLE `cards` (
  `id` int(3) NOT NULL,
  `cardNum` int(16) NOT NULL,
  `expiry` varchar(5) NOT NULL,
  `cvv` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cards`
--

INSERT INTO `cards` (`id`, `cardNum`, `expiry`, `cvv`) VALUES
(26, 1223, '05/30', 1232),
(17, 0, '05/30', 0),
(18, 0, '05/30', 0),
(19, 0, '05/30', 0),
(20, 0, '05/30', 0),
(21, 0, '05/30', 0),
(22, 0, '05/30', 0),
(23, 0, '05/30', 0),
(24, 0, '05/30', 0),
(25, 0, '05/30', 0);

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `genre_id` int(2) NOT NULL,
  `genre_name` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`genre_id`, `genre_name`) VALUES
(1, 'biography'),
(2, 'Thriller'),
(3, 'Romanace'),
(4, 'Comedy'),
(5, 'Non-Ficton');

-- --------------------------------------------------------

--
-- Table structure for table `loans`
--

CREATE TABLE `loans` (
  `loan_id` int(10) NOT NULL,
  `book_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `date_taken` date NOT NULL,
  `due_date` date NOT NULL,
  `date_returned` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loans`
--

INSERT INTO `loans` (`loan_id`, `book_id`, `user_id`, `date_taken`, `due_date`, `date_returned`) VALUES
(1, 1, 4, '2020-12-30', '2021-01-13', NULL),
(2, 1, 38, '2020-12-30', '2021-01-13', '2021-01-06'),
(3, 2, 38, '2020-12-30', '2021-01-13', NULL),
(4, 4, 38, '2020-12-08', '2020-12-22', NULL),
(5, 41, 38, '2021-01-01', '2021-01-15', '2021-01-13'),
(6, 12, 38, '2021-01-12', '2021-01-18', '2021-01-17'),
(7, 12, 38, '2021-01-12', '2021-01-18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `securityanswers`
--

CREATE TABLE `securityanswers` (
  `id` int(3) NOT NULL,
  `userId` int(3) NOT NULL,
  `question` int(3) NOT NULL,
  `answer` varchar(512) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `securityanswers`
--

INSERT INTO `securityanswers` (`id`, `userId`, `question`, `answer`) VALUES
(1, 44, 2, 'testAnswer');

-- --------------------------------------------------------

--
-- Table structure for table `securityquestions`
--

CREATE TABLE `securityquestions` (
  `id` int(3) NOT NULL,
  `question` varchar(130) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `securityquestions`
--

INSERT INTO `securityquestions` (`id`, `question`) VALUES
(1, 'What was the name of your childhood pet?'),
(2, 'Where did you go to primary school?'),
(3, 'What was the house number and street name you lived in as a child?'),
(4, 'What were the last four digits of your childhood telephone number?'),
(5, 'In what town or city was your first full time job?'),
(6, 'What is your grandmother\'s (on your mother\'s side) maiden name?'),
(7, 'What is your spouse or partner\'s mother\'s maiden name?'),
(8, 'When did you get your first car?');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(3) NOT NULL,
  `paymentDetails` int(3) DEFAULT NULL,
  `first_name` varchar(15) NOT NULL,
  `last_name` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(512) NOT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `access` varchar(10) NOT NULL DEFAULT 'disabled',
  `user_type` varchar(10) NOT NULL DEFAULT 'standard',
  `address` int(3) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `paymentDetails`, `first_name`, `last_name`, `email`, `username`, `password`, `phone`, `access`, `user_type`, `address`) VALUES
(42, 26, 'aobh', 'nolan', 'aobhnolan@gmail.com', 'staobh', '1000:3855a211d5190d89af3e29e85f0953b0:129daa9f649b239c205349c1844cd988ded849a9f670c90c03230576e5882c0b113cdcb683992d154b561b5d6cff2c7b924f15502e415363e7cf78b20f088a3e', '0852830828', 'disabled', 'standard', NULL),
(4, 0, '', '', 'bJohnson@123.ie', 'Briananj_1', '100000:a375ba50732e74dba7e2485a895535ac:c640fdfc9328a7fe624ee0cbac5cd4f95ab0dd7234a48b180b2d65c142765adc81125b9c36500cd0b73b2a8808c08c04d90e736c83ee69236232acf63c159e51', '', 'disabled', 'standard', 0),
(10, 5, '', '', 'garyBriody@gmail.com', 'gary', '1000:8b29f0e39c39d032d90c29213ae0fd89:bde4f7b6fa2ace9971160a2424b1cec5ca1c61df1b8796d136ddc03717a68ba54ac4be4b43e435da7c0ebf0254de2b1ad095a551266fac3647cba7cbf7105afb', '', 'disabled', 'standard', 0),
(11, 8, '', '', 'gillian@gmail.com', 'gillian', '1000:bbc38d78381fc33ad3863faac650748b:63a55de408ade30738971ebf8eb59822a9bf2c2001c13e3990d9d6a58f046dc67ee951d0bf255b557f947b172aae2ff9b8fdbea8108ac31116f288b452f465e0', '', 'disabled', 'standard', 0),
(12, 9, '', '', 'adamlawleess@gmail.com', 'adam', '1000:6137fe099fa95e3799c77351f2edad40:154a4dd729f2736e2a8dcbc026d5d79b82b7465858d2063a5ddae0735c6e7b8831d41e6966b5ab33e3b02cfdd13ac94c77781200b72c800eb282aff956c4ccb3', '', 'disabled', 'standard', 0),
(15, 12, '', '', 'lbriody10@gmail.com', 'gary', '1000:52a580a64c03f7b708b48fc8176cc94f:3a3dbdfcf78d4bb32ddc90521bda3f1e5dfcd6a689ea0e7163ada54bf85944434165417b7e092f715d406db76bd6c03989decb79796e3810258a4864ce8d5197', '', 'disabled', 'standard', 0),
(17, 14, '', '', 'muller@gmail.com', 'muller', '1000:60469e40cbf5e5899733f073ac4bbf80:331fab0033fb322445be50e0ac3d7076ee7a1df52dc11f80a6eaaf1ecd66cd0a28dc8d622c300b58e840d5fe8bbf4f1141a04d6ab77a363d98b90f3252536d91', '', 'disabled', 'standard', 0),
(18, 15, '', '', 'tiny@gmail.com', 'tiny', '1000:052080e45e6baa8fb4733f2481dcd926:7e78dc440e3d0e7561971bf265879948e4f302ab92815087b8d31138ac9c17999b8fd55d30117c3c6dbe493caef779a53d4956994ae23f20c9245a4712584e3c', '', 'disabled', 'standard', 0),
(27, 16, '', '', 'lbriody10@gmail.com', 'andy', '1000:dc035680d0a5b666ad9714f3eb3cd2dd:51536884ea8634aac3c785f83cad472c107f08c3a84997c6601017d08fc53435152ef092c41711cd192be73f0fb981d07a87227bd1a5d35a720538ed825e8c74', '', 'disabled', 'standard', 0),
(28, 16, '', '', 'briananjohnson@gmail.com', 'brianan', '1000:5b91cc46e35e885c9b5b0baf0b2e6343:04cbdbb98f18177c8760b7122fe573fda19504d4166ab09043f65e5852146eca6a3b5a13dba8871a2cc8b45032213d02ca877d1b1e313a2eed7a31afadea3ab0', '', 'disabled', 'standard', 0),
(38, 26, 'Osama', 'Kheireddine', 'osamakheireddine123@gmail.com', 'Osamakh', '1000:7560d2df345226e2942d99fc46386465:62eac2c156762f5e81069d218fa9581dad49088b4cf2e5fab959a07ba86bb76c91e14d2657440444aeabf0e94acf98de3472fcf37397556ab4a72f40677fbd7b', '10987654321', 'disabled', 'standard', NULL),
(30, 0, '', '', 'a.oshea30@gmail.com', 'AOS30', '1000:54bfafd1ba5d4cb36e5f5bda654f4308:05285184af4425cfdbdc97c0616d5c46b909e7bdae47e18a13b1cd3256b87b808a689a3b26721f89ffdc42ec012b02bf7634d64160848182a9369eadfb74d44b', '', 'disabled', 'standard', 0),
(41, 26, 'Daniel', 'Kheireddine', 'D.Kheireddine1@gmail.com', 'd.khereddine1', '1000:32eb071dcb9e319df9a31c9addcc0c01:10468f4a03fb41d4bfb2aba083ae7ef9bf1c739efc42895f8102f7434a4d9093942f9287fea8f6e99568e0d331dbad0272e219db937b5b1bbaa036d719f9ccb8', NULL, 'disabled', 'standard', NULL),
(37, 26, 'Vakaris', 'Urbanivicius', 'slickVik@gmail.com', 'VakarisU', '1000:dc843dd592faf99bcbeb8116aef8f4d0:3556cd8251dde05460c8e7eeca597f6b41529793b6fb818ed2a1f8e40748d1ad3325c871f2947305241a0367f70ac58d81aa575957209516a1996fb81dbe1883', NULL, 'disabled', 'standard', NULL),
(43, 26, 'Isa', 'Kheireddine', 'isak@email.com', 'Ikheir', '1000:075c5b564d1add9e02fbae25150763b9:de897b1986bc758d65fbec06ccd2f623ff1103f0650734e5768f7e6c16ab04c924b6e78cc96412c7f7dff81007f15de6aac6b91ff4711726095110fbb688454b', '1234567890', 'disabled', 'standard', NULL),
(44, 1000, 'testing', 'gue', 'testing@emaiol.com', 'test', 'password', '010101', 'disabled', 'standard', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`address_id`);

--
-- Indexes for table `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`author_id`);

--
-- Indexes for table `bookauthor`
--
ALTER TABLE `bookauthor`
  ADD PRIMARY KEY (`book_id`,`author_id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`genre_id`);

--
-- Indexes for table `loans`
--
ALTER TABLE `loans`
  ADD PRIMARY KEY (`loan_id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `securityanswers`
--
ALTER TABLE `securityanswers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userId` (`userId`),
  ADD KEY `question` (`question`);

--
-- Indexes for table `securityquestions`
--
ALTER TABLE `securityquestions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `paymentDetails` (`paymentDetails`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `book_id` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `loans`
--
ALTER TABLE `loans`
  MODIFY `loan_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `securityanswers`
--
ALTER TABLE `securityanswers`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
