-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 08-02-2023 a las 21:47:13
-- Versión del servidor: 5.7.36
-- Versión de PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `videoclub`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_pago`
--

DROP TABLE IF EXISTS `detalle_pago`;
CREATE TABLE IF NOT EXISTS `detalle_pago` (
  `idPago` int(11) NOT NULL AUTO_INCREMENT,
  `idPedido` int(11) NOT NULL,
  `formaPago` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `numeroTarjeta` int(15) NOT NULL,
  `fechaVencimiento` varchar(5) COLLATE utf8mb4_spanish_ci NOT NULL,
  `codigoSeguridad` int(50) NOT NULL,
  `precio` float NOT NULL,
  PRIMARY KEY (`idPago`),
  KEY `FK_Detalle_Pago_Pedido` (`idPedido`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `detalle_pago`
--

INSERT INTO `detalle_pago` (`idPago`, `idPedido`, `formaPago`, `numeroTarjeta`, `fechaVencimiento`, `codigoSeguridad`, `precio`) VALUES
(10, 1, 'mastercard', 12545865, '08/24', 852, 15.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

DROP TABLE IF EXISTS `genero`;
CREATE TABLE IF NOT EXISTS `genero` (
  `idGenero` int(2) NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`idGenero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`idGenero`, `nombre`) VALUES
(1, 'Accion'),
(2, 'Terror'),
(3, 'Animacion'),
(4, 'Misterio'),
(5, 'Comedia'),
(6, 'Drama'),
(7, 'Documental'),
(8, 'Suspense'),
(9, 'Fantasia'),
(10, 'Musical'),
(11, 'Oeste'),
(12, 'Ciencia Ficcion'),
(13, 'Crimen');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_pedido`
--

DROP TABLE IF EXISTS `linea_pedido`;
CREATE TABLE IF NOT EXISTS `linea_pedido` (
  `idLineapedido` int(11) NOT NULL AUTO_INCREMENT,
  `idProducto` int(50) NOT NULL,
  `idPedido` int(11) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `precioTotal` float NOT NULL,
  PRIMARY KEY (`idLineapedido`),
  KEY `FK_Linea_Pedido` (`idPedido`),
  KEY `FK_Linea_Producto` (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `linea_pedido`
--

INSERT INTO `linea_pedido` (`idLineapedido`, `idProducto`, `idPedido`, `cantidad`, `precioTotal`) VALUES
(32, 1, 1, 1, 3.5),
(33, 2, 1, 2, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE IF NOT EXISTS `pedido` (
  `idPedido` int(11) NOT NULL,
  `precio` float NOT NULL,
  `fecha` date NOT NULL,
  `correoUsuario` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombrePedido` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `FK_Pedido_Usuarios` (`correoUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`idPedido`, `precio`, `fecha`, `correoUsuario`, `nombrePedido`) VALUES
(1, 15.5, '2023-02-08', 'ikerperezcarcamo1921@gmail.com', 'Pedido');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `descripcion` varchar(2000) COLLATE utf8mb4_spanish_ci NOT NULL,
  `numUnidades` int(2) NOT NULL,
  `idGenero` int(2) NOT NULL,
  `precioVentas` float NOT NULL,
  `idProducto` int(50) NOT NULL AUTO_INCREMENT,
  `descuento` tinyint(1) NOT NULL,
  `foto` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL,
  `proveedor` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `tipo` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `FK_Producto_Categoria` (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`nombre`, `descripcion`, `numUnidades`, `idGenero`, `precioVentas`, `idProducto`, `descuento`, `foto`, `proveedor`, `tipo`) VALUES
('Avatar The Way of Water ', 'Ambientada mas de una decada despues de los acontecimientos de la primera pelicula, empieza contando la historia de la familia Sully (Jake, Neytiri y sus hijos), los problemas que los persiguen, lo que tienen que hacer para mantenerse a salvo, las batallas que libran para seguir con vida y las tragedias que sufren.', 203, 12, 7.68, 1, 50, 'imagenes/imagenesProductos/Avatar The Way of Water.jpg', 'porcelanosa', 'pelicula'),
('Devotion', 'Basada en hechos reales. En 1950, cuando la Guerra Fria amenaza la paz internacional, dos jovenes pilotos de diferentes mundos son aceptados en un escuadron de elite para su entrenamiento: uno es Tom Hudner (Glen Powell), un soldado impecable. El otro es Jesse Brown (Jonathan Majors), un talentoso piloto, que se convertiria en el primer afroamericano en volar en combate para la Marina de los Estados Unidos. Iniciados juntos en el escuadron VF-32, Tom y Jesse son llevados al limite para convertirse en los mejores pilotos de combate. donde formaran una firme amistad.', 7, 7, 6.45, 2, 0, 'imagenes/imagenesProductos/Devotion.jpg', 'txipivision', 'pelicula'),
('Finding Nemo', 'Nemo, un pececillo, hijo unico muy querido y protegido por su padre, ha sido capturado en un arrecife australiano y ahora vive en una pecera en la oficina de un dentista de Sidney. El timido padre de Nemo se embarcara en una peligrosa aventura para rescatar a su hijo. Pero Nemo y sus nuevos amigos tienen tambien un astuto plan para escapar de la pecera y volver al mar.', 175, 3, 8.15, 3, 50, 'imagenes/imagenesProductos/Finding Nemo.jpg', 'telemadrid', 'pelicula'),
('High Heat', 'Cuando la mafia local aparece para incendiar su restaurante, Ana, una chef con un pasado meticuloso, defiende su territorio y demuestra sus habilidades con el cuchillo tanto dentro como fuera de la cocina.', 252, 1, 4.93, 4, 0, 'imagenes/imagenesProductos/High Heat.jpg', 'warner', 'pelicula'),
('Inspector Sun and the Curse of the Black Widow', 'Shanghai, 1934. En un mundo de insectos paralelo al de los humanos, el veterano Inspector Sun, una araña solitaria, lidera su ultima mision contra su archienemigo Langosta Roja, antes de embarcarse en un merecido retiro. Sus vacaciones a bordo de un avion Pan Am clipper entre Shanghai y San Francisco acaban abruptamente cuando el millonario Dr. Spindelthorp aparece asesinado. Lo que comienza como un caso rutinario para Sun, decidira finalmente el destino de la humanidad.', 0, 3, 5.75, 5, 50, 'imagenes/imagenesProductos/Inspector Sun and the Curse of the Black Widow.jpg', 'porcelanosa', 'pelicula'),
('L.O.L. Surprise: The Movie', 'Las deslumbrantes hermanas muñecas Queen Bee y Royal Bee hacen su primera pelicula con la ayuda de sus amigas de moda en esta aventura magica unica.', 234, 3, 4.4, 6, 0, 'imagenes/imagenesProductos/L.O.L. Surprise The Movie.jpg', 'txipivision', 'pelicula'),
('Lyle, Lyle, Crocodile', 'Cuando la familia Primm se muda a Nueva York, su hijo adolescente Josh lucha por adaptarse a su nueva escuela y nuevos amigos. Todo eso cambia cuando descubre a Lilo – un cocodrilo cantante a quien le gusta darse baños, el caviar y la buena musica – viviendo en el atico de su nueva casa. Los dos se convierten en mejores amigos, pero cuando la existencia de Lilo se ve amenazada por su malvado vecino Mr. Grumps, los Primm deberan aliarse con el carismatico dueño de Lilo, Hector P. Valenti, para mostrar al mundo que la familia puede surgir de los lugares mas inesperados y que no hay nada malo con un gran cocodrilo cantante con una personalidad aun mayor.', 244, 10, 6.28, 7, 50, 'imagenes/imagenesProductos/Lyle, Lyle, Crocodile.jpg', 'telemadrid', 'pelicula'),
('Operation Fortune Ruse de guerre', 'La historia de un agente que es reclutado por una alianza de inteligencia global para rastrear y detener la venta de nuevas tecnologias de armas mortales.', 256, 8, 6.15, 8, 0, 'imagenes/imagenesProductos/Operation Fortune Ruse de guerre.jpg', 'warner', 'pelicula'),
('Paw Patrol The Movie ', 'La patrulla canina esta en racha. Cuando Humdinger, su mayor rival, se convierte en alcalde de la cercana Ciudad Aventura y empieza a causar estragos, Ryder y los heroicos cachorros se ponen en marcha para enfrentarse a este nuevo desafio. Mientras uno de los cachorros debe enfrentarse a su pasado en Ciudad Aventura, el equipo encuentra ayuda en una nueva aliada, la inteligente perrita salchicha Liberty. Juntos y armados con nuevos y emocionantes artefactos y equipos, la patrulla canina lucha por salvar a los ciudadanos de Ciudad Aventura.', 269, 3, 6.53, 9, 50, 'imagenes/imagenesProductos/Paw Patrol The Movie.jpg', 'porcelanosa', 'pelicula'),
('Paws of Fury The Legend of Hank ', 'Una comedia animada llena de accion para toda la familia inspirada en el clasico atemporal de Mel Brooks. Hank, un perro encantador con la cabeza llena de sueños sobre convertirse en samurái, parte en busca de su destino.', 271, 5, 5.93, 10, 0, 'imagenes/imagenesProductos/Paws of Fury The Legend of Hank.jpg', 'txipivision', 'pelicula'),
('Prey for the Devil', 'Una joven monja, la hermana Ann (Jacqueline Byers), se prepara para un exorcismo y se enfrenta a una fuerza demoniaca que está misteriosamente relacionada con su pasado.', 666, 2, 5.53, 11, 50, 'imagenes/imagenesProductos/Prey for the Devil.jpg', 'telemadrid', 'pelicula'),
('Puss in Boots The Last Wish', 'Secuela del  gato con botas (2011). El Gato con Botas descubre que su pasion por la aventura le ha pasado factura: ha consumido ocho de sus nueve vidas, por ello emprende un viaje epico para encontrar el mitico Ultimo Deseo y restaurar sus nueve vidas...', 296, 9, 8, 12, 0, 'imagenes/imagenesProductos/Puss in Boots The Last Wish.jpg', 'warner', 'pelicula'),
('Sex Appeal', 'Avery, una adolescente con tendencia al perfeccionismo, recluta a su amigo Larson para que la ayude a prepararse para su primera vez con su novio a larga distancia.', 300, 5, 5.5, 13, 50, 'imagenes/imagenesProductos/Sex Appeal.jpg', 'porcelanosa', 'pelicula'),
('Sex Doll', 'Situada en el mundo de las escorts de lujo en Londres, cuenta la historia de Virginie y de Rupert, un hombre que rescata a las chicas de la trata de blancas.', 317, 6, 4.88, 14, 0, 'imagenes/imagenesProductos/Sex Doll.jpg', 'txipivision', 'pelicula'),
('Shotgun Wedding', 'Darcy (Jennifer Lopez) y Tom (Josh Duhamel) viajan con sus familias para celebrar su boda, pero cuando todos los invitados son tomados como rehenes, la frase «hasta que la muerte nos separe» adquiere un nuevo significado en esta desternillante aventura cargada de adrenalina en la que Darcy y Tom tendran que salvar a sus seres queridos, si no se matan entre ellos antes.', 323, 6, 5.5, 15, 50, 'imagenes/imagenesProductos/Shotgun Wedding.jpg', 'telemadrid', 'pelicula'),
('Spirited', 'Una version musical de la historia clasica de Charles Dickens de un misantropo avaro que es llevado a un viaje magico. Cada vispera de Navidad, el fantasma del espiritu de la Navidad selecciona un alma oscura para ser reformada por una visita de tres espiritus. Pero esta temporada, ha elegido al Scrooge equivocado... Por primera vez, el clasico A Christmas Carol se cuenta desde la perspectiva de los fantasmas en este musical sobre el cuento de Dickens.', 333, 1, 6, 16, 0, 'imagenes/imagenesProductos/Spirited.jpg', 'warner', 'pelicula'),
('The Enforcer', 'Cuda, un sicario de la mafia de Miami, debera sacrificarlo todo para salvar a una joven que se ha juntado con quien no debia: la jefa de la organizacion criminal para la que el trabaja. El protagonista se vera obligado a tirar de contactos, y otros recursos menos ortodoxos, para salvar a la chica y resolver sus problemas.', 346, 1, 5.68, 17, 50, 'imagenes/imagenesProductos/The Enforcer.jpg', 'porcelanosa', 'pelicula'),
('The Old Way', 'Un viejo pistolero y su hija deben enfrentar las consecuencias de su pasado, cuando el hijo de un hombre al que asesino años atrás llega para vengarse.', 288, 11, 5.4, 18, 0, 'imagenes/imagenesProductos/The Old Way.jpg', 'txipivision', 'pelicula'),
('Violent Night', 'Cuando un equipo de mercenarios irrumpe en Nochebuena dentro de un complejo familiar adinerado y toma como rehenes a todos los que estan dentro, no estaban preparados para un defensor sorpresa: Santa Claus (David Harbour) esta en el edificio y a punto de demostrar por que este Santa Claus, no es ningun santo.', 334, 1, 6.7, 19, 50, 'imagenes/imagenesProductos/Violent Night.jpg', 'telemadrid', 'pelicula'),
('Watcher', 'Una joven se muda a un nuevo apartamento con su prometido y se ve atormentada por la sensacion de ser acosada por un vigilante invisible en un edificio adyacente.', 129, 2, 6.2, 20, 0, 'imagenes/imagenesProductos/Watcher.jpg', 'warner', 'pelicula'),
('American Horror Story', 'American Horror Story es una serie antologica de television, creada y producida por Ryan Murphy y Brad Falchuk. Cada una de sus temporadas narra una trama independiente, ambientada en lugares diferentes y cerrando siempre la historia; donde muchos actores repiten, pero siempre interpretando personajes distintos.', 203, 2, 7.33, 21, 50, 'imagenes/imagenesProductos/American Horror Story.jpg', 'warner', 'serie'),
('Better Call Saul ', 'Esta precuela de Breaking Bad nominada al Emmy narra la vida del picapleitos Jimmy McGill y su transformacion en Saul Goodman, el abogado de moral laxa.', 194, 13, 8.48, 22, 0, 'imagenes/imagenesProductos/Better Call Saul.jpg', 'porcelanosa', 'serie'),
('Black Mirror', 'Black Mirror es una serie de television britanica creada por Charlie Brooker que muestra el lado oscuro de la vida y la tecnologia. La serie es producida por Zeppotron para Endemol. En cuanto al contenido del programa y la estructura, Brooker ha señalado que cada episodio tiene un tono diferente, un entorno diferente, incluso una realidad diferente, pero todos son acerca de la forma en que vivimos ahora - y la forma en que podriamos estar viviendo en 10 minutos si somos torpes.', 170, 2, 8.3, 23, 50, 'imagenes/imagenesProductos/Black Mirror.jpg', 'txipivision', 'serie'),
('Breaking Bad', 'Tras cumplir 50 años, Walter White (Bryan Cranston), un profesor de quimica de un instituto de Albuquerque, Nuevo Mexico, se entera de que tiene un cancer de pulmon incurable. Casado con Skyler (Anna Gunn) y con un hijo discapacitado (RJ Mitte), la brutal noticia lo impulsa a dar un drastico cambio a su vida: decide, con la ayuda de un antiguo alumno (Aaron Paul), fabricar anfetaminas y ponerlas a la venta. Lo que pretende es liberar a su familia de problemas economicos cuando se produzca el fatal desenlace.', 389, 6, 9.1, 24, 0, 'imagenes/imagenesProductos/Breaking Bad.jpg', 'telemadrid', 'serie'),
('Code Lyoko', 'En una academia de Boulogne-Billancourt (Francia) llamada Kadic, un alumno muy inteligente (superdotado), Jeremie Belpois, quiere adquirir materiales para la construccion de unos robots. Para conseguirlos, se dirige hacia una fabrica abandonada muy proxima a su academia donde encuentra un superordenador cuantico que pone en marcha. Al encender este superordenador, descubre un mundo virtual llamado Lyoko dividido en cuatro sectores (hielo, desierto, bosque y montañas), asi como tambien la existencia de un virus informatico malvado llamado XANA, y de una chica de su edad que se encuentra “virtualizada” llamada Aelita. Este hallazgo es comunicado a Ulrich Stern, Odd Della Robbia y Yumi Ishiyama, alumnos de la academia, que lucharan contra XANA.', 1921, 12, 6.53, 25, 50, 'imagenes/imagenesProductos/Code Lyoko.jpg', 'txipivision', 'serie'),
('Dark', 'Tras la desaparicion de un niño, cuatro familias desesperadas tratan de entender lo ocurrido a medida que van desvelando un retorcido misterio que abarca tres decadas.', 251, 4, 8.33, 26, 0, 'imagenes/imagenesProductos/Dark.jpg', 'txipivision', 'serie'),
('Elite', 'Las Encinas, un exclusivo colegio privado al que la elite social del pais envia a sus hijos. Pero en el centro son admitidos tres adolescentes de familias humildes despues de que un terremoto destruyera el colegio publico en el que estudiaban. El choque de clases genera diversos problemas que se agravan hasta que, de repente, se produce un asesinato.', 263, 4, 7.03, 27, 50, 'imagenes/imagenesProductos/Elite.jpg', 'txipivision', 'serie'),
('Game of Thrones', 'En una tierra donde los veranos duran decadas y los inviernos pueden durar toda una vida, los problemas acechan. Desde las maquinaciones del sur a las salvajes tierras del este, pasando por el helado norte y el milenario muro que protege el reino de las fuerzas tenebrosas, dos poderosas familias mantienen un enfrentamiento letal por gobernar los Siete Reinos de Poniente. Mientras la traicion, la lujuria y las fuerzas sobrenaturales sacuden los pilares de los reinos, la sangrienta batalla por el trono de Hierro tendra consecuencias imprevistas y trascendentales. El invierno se acerca.', 276, 1, 8.83, 28, 0, 'imagenes/imagenesProductos/Game of Thrones.jpg', 'porcelanosa', 'serie'),
('La casa de papel', 'Un enigmatico personaje llamado el Profesor planea algo grande: llevar a cabo el mayor atraco de la historia. Para ello recluta una banda de ocho personas que reunen un unico requisito, ninguno tiene nada que perder. Cinco meses de reclusion memorizando cada paso, cada detalle, cada probabilidad… y por fin, once dias de encierro en la Fabrica Nacional de Moneda, rodeados de cuerpos de policia y con decenas de rehenes en su poder para saber si su apuesta suicida sera todo o nada.', 288, 6, 7.95, 29, 50, 'imagenes/imagenesProductos/La casa de papel.jpg', 'txipivision', 'serie'),
('Prison Break', 'Michael Scofield (Wentworth Miller) es un hombre desesperado en un situacion desesperada. Su hermano Lincoln Burrows (Dominic Purcell), condenado a la pena capital esta a la espera de ser ejecutado. A pesar de todas las evidencias, Michael cree en su inocencia, por lo que decide robar un banco para dejarse atrapar y ser encarcelado en la misma prision que su hermano. Su objetivo: escapar juntos.', 301, 1, 8.8, 30, 0, 'imagenes/imagenesProductos/Prison Break.jpg', 'telemadrid', 'serie'),
('Squid Game', 'Cientos de jugadores con problemas de dinero aceptan una invitacion rarisima para competir en juegos infantiles. Dentro les esperan un tentador premio y desafios letales.', 314, 6, 7.65, 31, 50, 'imagenes/imagenesProductos/Squid Game.jpg', 'warner', 'serie'),
('Stranger Things', 'A raiz de la desaparicion de un niño, un pueblo desvela un misterio relacionado con experimentos secretos, fuerzas sobrenaturales aterradoras y una niña muy extraña.', 326, 2, 8.48, 32, 0, 'imagenes/imagenesProductos/Stranger Things.jpg', 'porcelanosa', 'serie'),
('The Walking Dead ', 'Esta ambientada en un futuro apocaliptico con la Tierra devastada por el efecto de un cataclismo, que ha provocado la mutación en zombies de la mayor parte de los habitantes del planeta. La serie, explora las dificultades de los protagonistas para sobrevivir en un mundo poblado por el horror, asi como las relaciones personales que se establecen entre ellos, en ocasiones tambien una amenaza para su supervivencia.', 339, 1, 8, 33, 50, 'imagenes/imagenesProductos/The Walking Dead.jpg', 'txipivision', 'serie'),
('Vikings', 'Sigue las aventuras de Ragnar Lothbrok, el heroe mas grande de su epoca. La serie narra las sagas de la banda de hermanos vikingos de Ragnar y su familia, cuando el se levanta para convertirse en el rey de las tribus vikingas. Ademas de ser un guerrero valiente, Ragnar encarna las tradiciones nordicas de la devocion a los dioses, la leyenda dice que el era un descendiente directo de Odin, el dios de la guerra y los guerreros.', 351, 1, 7.95, 34, 0, 'imagenes/imagenesProductos/Vikings.jpg', 'telemadrid', 'serie');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `apellidos` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `direccion` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `codigoPostal` int(5) NOT NULL,
  `municipio` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `ciudad` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `pais` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `correo` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `telefono` varchar(15) COLLATE utf8mb4_spanish_ci NOT NULL,
  `admin` int(1) NOT NULL,
  PRIMARY KEY (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nombre`, `apellidos`, `direccion`, `codigoPostal`, `municipio`, `ciudad`, `pais`, `correo`, `password`, `telefono`, `admin`) VALUES
('admin', 'admin', 'admin', 12345, 'admin', 'admin', 'admin', 'admin@admin.com', 'admin', '1', 1),
('txipi', 'ron', 'txipiron', 12312, 'munisipio', 'siuda', 'pai', 'ikerperezcarcamo1921@gmail.com', 'ejemploAdmin', '0', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoraciones`
--

DROP TABLE IF EXISTS `valoraciones`;
CREATE TABLE IF NOT EXISTS `valoraciones` (
  `idProducto` int(50) NOT NULL,
  `notaIMDB` float NOT NULL,
  `notaFILMAFFINTY` float NOT NULL,
  `notaSENSACINE` float NOT NULL,
  `notaTMDB` float NOT NULL,
  PRIMARY KEY (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `valoraciones`
--

INSERT INTO `valoraciones` (`idProducto`, `notaIMDB`, `notaFILMAFFINTY`, `notaSENSACINE`, `notaTMDB`) VALUES
(1, 7.8, 6.8, 8.4, 7.7),
(2, 6.7, 5.5, 6.2, 7.4),
(3, 8.1, 7.3, 9.4, 7.8),
(4, 4.7, 4.6, 4.9, 5.5),
(5, 5.8, 4.8, 5.6, 6.9),
(6, 3.5, 3.2, 4, 6.9),
(7, 6.1, 5.3, 6, 7.7),
(8, 6.7, 6.1, 5, 6.8),
(9, 6.2, 5.4, 7, 7.5),
(10, 5.7, 5.2, 6, 6.8),
(11, 5.2, 4.5, 5.2, 7.2),
(12, 7.8, 7.2, 8.4, 8.6),
(13, 5.3, 4.8, 5.2, 6.7),
(14, 4.5, 4.9, 4.9, 5.2),
(15, 5.9, 4.2, 6, 6.1),
(16, 6.6, 5.5, 5, 7.1),
(17, 5.2, 4.4, 6, 7.1),
(18, 5.3, 4.8, 5.4, 6.1),
(19, 6.7, 5.8, 6.6, 7.7),
(20, 6.3, 5.9, 6, 6.6),
(21, 8, 4.9, 8.2, 8.2),
(22, 8.9, 7.8, 8.6, 8.6),
(23, 8.8, 8.2, 8.6, 7.6),
(24, 9.5, 8.8, 9.2, 8.9),
(25, 7.3, 4.7, 6.6, 7.5),
(26, 8.7, 7.6, 8.6, 8.4),
(27, 7.3, 5.5, 7.2, 8.1),
(28, 9.2, 8.5, 9.2, 8.4),
(29, 8.2, 6.9, 8.4, 8.3),
(30, 8.3, 7.3, 8.6, 8.1),
(31, 8, 7, 7.8, 7.8),
(32, 8.7, 7.8, 8.8, 8.6),
(33, 8.1, 7, 8.8, 8.1),
(34, 7.2, 7.5, 9, 8.1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_pago`
--
ALTER TABLE `detalle_pago`
  ADD CONSTRAINT `FK_Detalle_Pago_Pedido` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`idPedido`);

--
-- Filtros para la tabla `linea_pedido`
--
ALTER TABLE `linea_pedido`
  ADD CONSTRAINT `FK_Linea_Pedido` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`idPedido`),
  ADD CONSTRAINT `FK_Linea_Producto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_Pedido_Usuarios` FOREIGN KEY (`correoUsuario`) REFERENCES `usuario` (`correo`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `idGenero` FOREIGN KEY (`idGenero`) REFERENCES `genero` (`idGenero`);

--
-- Filtros para la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD CONSTRAINT `idPro` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
