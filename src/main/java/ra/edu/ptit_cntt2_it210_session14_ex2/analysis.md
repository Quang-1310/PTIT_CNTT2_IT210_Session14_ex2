Khi không sử dụng Transaction, mỗi lệnh gọi xuống Database sẽ được thực hiện một cách độc lập và tức thì
Lệnh thứ nhất: order.setStatus("CANCELLED"); session.merge(order); -> Hibernate gửi lệnh UPDATE trạng thái đơn hàng xuống DB thành công. Lúc này, Database đã ghi nhận đơn hàng bị hủy.

Lệnh thứ hai: Product product = session.find(Product.class, order.getProductId()); -> Giả sử getProductId() trả về null hoặc không tìm thấy sản phẩm, một Exception sẽ bị ném ra.

Kết quả: Chương trình dừng lại ở lệnh thứ hai và nhảy vào khối catch. Tuy nhiên, lệnh thứ nhất đã được Database thực thi và lưu lại vĩnh viễn trước đó rồi. Không có phương thức rollback để cho trạng thái đơn hàng quay lại trạng thái ban đâu
