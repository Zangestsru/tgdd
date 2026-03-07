package com.example.NgoDangKhoa_2280601515.component;

import com.example.NgoDangKhoa_2280601515.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CategoryDataSeeder implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            String sql1 = """
                    INSERT IGNORE INTO categories (id, name, parentId, imageurl) VALUES
                    (1, 'Phụ kiện di động', NULL, NULL),
                    (2, 'Phụ kiện laptop, PC', NULL, NULL),
                    (3, 'Thiết bị âm thanh', NULL, NULL),
                    (4, 'Camera', NULL, NULL),
                    (5, 'Phụ kiện gaming', NULL, NULL),
                    (6, 'Thiết bị lưu trữ', NULL, NULL),
                    (7, 'Phụ kiện khác', NULL, NULL),
                    (8, 'Thương hiệu hàng đầu', NULL, NULL);
                    """;

            String sql2 = """
                    INSERT IGNORE INTO categories (name, parentId, imageurl) VALUES
                    ('Sạc dự phòng', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/99/61/9961578164909f8a9ee7678dc95feeb0.png'),
                    ('Sạc, cáp', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/6b/64/6b646ec5f1e9a726933ee31b86a32524.png'),
                    ('Ốp lưng điện thoại', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/34/02/3402dd9ba3457b84482572d10bcae84e.png'),
                    ('Ốp lưng máy tính bảng', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/83/60/836050bcf5e1c92dd8d9899bef9f039d.png'),
                    ('Miếng dán', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/72/f4/72f4b3ee8f5c1b1a170d590b3a07256d.png'),
                    ('Miếng dán Camera', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/33/77/33770e364079ac9dd3888190bd574b8d.png'),
                    ('Túi đựng AirPods', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/24/66/2466de3fc4831f43afb0d69462130030.png'),
                    ('AirTag, Vỏ bảo vệ AirTag', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/27/e7/27e7538fb93c10e768cd0344ee8f8cd9.png'),
                    ('Bút tablet', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/3f/d5/3fd5723ac6f01521bd3d768d8ebc8d1a.png'),
                    ('Giá đỡ điện thoại/laptop/máy tính bảng', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/26/6f/266f538d2cee7899d54a8b5f57985d83.png'),
                    ('Dây đeo điện thoại', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/01/d1/01d140b8ea3be3cdbf4b99299fd14969.png'),
                    ('Ống kinh điện thoại', 1, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/ff/9a/ff9a1716ada03c1b0c3498b9bd6b6373.png'),
                    ('Hub, cáp chuyển đổi', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/e1/2d/e12deafa7615646e9cafc5bbd0667da8.png'),
                    ('Chuột máy tính', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/53/a6/53a6599a6fc414025b42c5435928008f.png'),
                    ('Bàn phím', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/7a/d3/7ad3598d5e291815bc6c7f98bb73d078.png'),
                    ('Router - Thiết bị mạng', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/f9/32/f93258b536d9a7c59b7746e533fb271f.png'),
                    ('Balo, túi chống sốc', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/33/8b/338bb7d3763dee703562a108b497fc2f.png'),
                    ('Túi đựng phụ kiện', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/9e/d6/9ed6b66919dccf3d26fc864184149913.png'),
                    ('Phủ phím laptop', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/c3/97/c397a6f5e562a5f3ad3084e4563d6dda.png'),
                    ('Phần mềm', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/bb/07/bb07512d2429a1b38cedd20b750b734c.png'),
                    ('Giá treo màn hình', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/7c/a7/7ca750e732738e17142f501ba33eb423.png'),
                    ('Đèn thông minh/livestream', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/b9/ed/b9ed8fbbb77742a0b721bbf2bc6ca8ef.png'),
                    ('Miếng lót chuột', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/b3/0e/b30ead80f9b691b781060ec46e8e2928.png'),
                    ('Bảng vẽ điện tử', 2, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/58/c0/58c0179556e1f44469d351ad90cb325e.png'),
                    ('Tai nghe Bluetooth', 3, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/7c/09/7c09cbc92ef23816aa7d857ba8e0e194.png'),
                    ('Tai nghe dây', 3, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/12/1c/121cd7cb1fc1750893b3f41436b12c85.png'),
                    ('Tai nghe chụp tai', 3, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/cf/9e/cf9e0eecbfc3e326f1c89f13b1ffe320.png'),
                    ('Tai nghe thể thao', 3, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/38/f7/38f7bf684502b5aa0c7949f6d38a6a55.png'),
                    ('Loa', 3, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/76/6b/766be9586a3a82491ba8106b7e558605.png'),
                    ('Micro', 3, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/c7/13/c71369bc9b7b00cd99d8c8076e5a9e7e.png'),
                    ('Camera Giám Sát', 4, 'https://cdnv2.tgdd.vn/mwg-static/tgdd/Common/af/70/af70e11a54ceeda4d04c41298016f615.png'),
                    ('Camera trong nhà', 4, 'https://cdnv2.tgdd.vn/mwg-static/tgdd/Common/e8/0c/e80cc810cf5d743dc7e88c7031380c80.png'),
                    ('Camera ngoài trời', 4, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/9e/e9/9ee9ae20f38eff97221f040a735752ce.png'),
                    ('Camera hành trình / hành động', 4, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/f4/2a/f42aa8a01e29d247b177a997c808c990.png'),
                    ('Camera Năng Lượng Mặt Trời', 4, 'https://cdnv2.tgdd.vn/mwg-static/tgdd/Common/5b/46/5b46309f2e80ca497b76f239ccf18829.png'),
                    ('Camera 4G', 4, 'https://cdnv2.tgdd.vn/mwg-static/tgdd/Common/18/9f/189f4355b6af9e13a9a6d8a05e862ce5.png'),
                    ('Chuột gaming', 5, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/4d/c5/4dc5692050284aa3119430c8b77a1a41.png'),
                    ('Bàn phím gaming', 5, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/7c/f2/7cf2df3939fd6d8966c1965cc781d02b.png'),
                    ('Tai nghe gaming', 5, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/25/71/2571e5bc85393230a9d8c793c0fb6ad3.png'),
                    ('Miếng lót chuột', 5, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/49/70/497053f8d25c1602c5c79faf15ac2049.png'),
                    ('Tay cầm chơi game', 5, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/0c/9e/0c9e064d8ec9452a931d021dfee96c79.png'),
                    ('Ổ cứng di động', 6, 'https://cdnv2.tgdd.vn/mwg-static/tgdd/Common/c1/4e/c14eb68c77b7402be257c972a8a9625b.png'),
                    ('Thẻ nhớ', 6, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/0b/e8/0be891a66b3f2fdf30a3a18070d1453a.png'),
                    ('USB', 6, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/34/b6/34b670e9f0f45c2d0290f41c5e747c28.png'),
                    ('Máy chiếu', 7, 'https://cdnv2.tgdd.vn/mwg-static/tgdd/Common/32/2f/322fcac70beaf1bb7c5925f0dc8663ac.png'),
                    ('Gimbal', 7, 'https://cdnv2.tgdd.vn/mwg-static/tgdd/Common/90/1a/901ad6c88ccafc404df1fdca08ac6ceb.png'),
                    ('Phụ kiện ô tô', 7, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/9c/8e/9c8e5c73e245a377862049b483cb3b21.png'),
                    ('Máy chụp ảnh lấy liền', 7, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/58/96/5896ea953c2a15fd8bebab2f35fdb47f.png'),
                    ('Flycam', 7, 'https://cdnv2.tgdd.vn/mwg-static/tgdd/Common/00/34/00347116e3b25ffa8e81d216607ada64.png'),
                    ('Pin tiểu', 7, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/d9/89/d9895ecb4c3fe80fa6a5788548c0d40d.png'),
                    ('Quạt mini', 7, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/07/ab/07ab4544063dddcba20bf16c8066745d.png'),
                    ('Quạt sưởi', 7, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/5b/81/5b815a1ca0cf9792245e3d8b03e05f6d.png'),
                    ('Đèn năng lượng mặt trời', 7, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/cf/ec/cfec82e82109dd1f4f889a5df9aaad38.png'),
                    ('Dây đeo đồng hồ', 7, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/b5/a5/b5a531c9008b87379f8f2cae7c37cd1c.png'),
                    ('Apple', 8, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/6a/6a/6a6a116227ceaf2f407f5573f44069ec.png'),
                    ('Samsung', 8, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/ea/1d/ea1d0470faaea58604610926a4f45fcb.png'),
                    ('Imou', 8, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/ee/a1/eea14df76a63a0f6b9f3267143856602.png'),
                    ('Baseus', 8, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/0a/bd/0abddded6e3650bcd1859c511fcc2747.png'),
                    ('JBL', 8, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/be/88/be887406c072668c452a41be86574976.png'),
                    ('Anker', 8, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/67/bd/67bd9cf55ca10673bf3df7605e295bb4.png'),
                    ('Xmobile', 8, 'https://cdnv2.tgdd.vn/mwg-static/common/Common/c2/26/c22643c4c1ee6d06a78a4aebea060e37.png');
                    """;

            // Updates to correct showInAccessories value directly using sql
            String sql3 = "UPDATE categories SET show_in_accessories = true WHERE parentId IS NOT NULL;";

            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS categories (" +
                    "    id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "    name VARCHAR(255) NOT NULL," +
                    "    parentId BIGINT DEFAULT NULL," +
                    "    show_in_accessories BOOLEAN DEFAULT false," +
                    "    imageurl VARCHAR(500) DEFAULT NULL" +
                    ");");
            jdbcTemplate.execute(sql1);
            jdbcTemplate.execute(sql2);
            jdbcTemplate.execute(sql3);
        }
    }
}
