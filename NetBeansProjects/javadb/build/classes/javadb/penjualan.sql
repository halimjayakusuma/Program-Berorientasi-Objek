CREATE TABLE `buku` (
  `id` int(11) NOT NULL,
  `judul buku` varchar(255) NOT NULL,
  `tahun terbit` int(4) NOT NULL,
  `stok` tinyint(2) NOT NULL,
  `penulis` int(11) NOT NULL
)

CREATE TABLE `penulis` (
  `id` int(11) NOT NULL,
  `nama penulis` varchar(255) NOT NULL
)