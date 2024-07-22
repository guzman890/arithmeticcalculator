SET NAMES utf8mb4;

INSERT INTO
    `operations` (`id`, `label`, `type`, `symbol`, `cost`)
VALUES
    (1, 'Addition', 'ADDITION', '+', 10),
    (2, 'Subtraction', 'SUBTRACTION', '-', 10),
    (3, 'Multiplication', 'MULTIPLICATION', 'x', 20),
    (4, 'Division', 'DIVISION', '/', 20),
    (5, 'Square Root', 'SQUARE_ROOT','sqr', 40),
    (6, 'Generate String', 'RANDOM_STRING', 's', 40);