INSERT INTO `author`
(`id`,`first_name`,`last_name`)
VALUES
    (1,'John1','Miller1'),
    (2,'John2','Miller2'),
    (3,'John3','Miller3');

INSERT INTO `article_content`
(`id`,`title`,`content`)
VALUES
    (1,'myTitle','myContent'),
    (2,'this is myTitle','myContent'),
    (3,'myTitle','thisIsMyContent');

INSERT INTO `press_article`
(`id`, `name`, `publication_date`, `save_date`,`article_content_id`,`author_id`)
VALUES
    (1,'myArticle1','2022-01-02 12:30:00.000000', '2023-02-16 17:21:21.759569', 1, 1),
    (2,'myArticle2','2012-01-01 12:40:00.000000', '2023-02-16 17:21:21.759569', 2, 2),
    (3,'myArticle3','2018-01-03 12:50:00.000000', '2023-02-16 17:21:21.759569', 3, 3);