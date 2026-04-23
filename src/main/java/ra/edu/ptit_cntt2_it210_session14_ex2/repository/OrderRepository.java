package ra.edu.ptit_cntt2_it210_session14_ex2.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import ra.edu.ptit_cntt2_it210_session14_ex2.model.Order;
import ra.edu.ptit_cntt2_it210_session14_ex2.model.Product;

@Repository
public class OrderRepository {
    @Autowired
    private SessionFactory sessionFactory;
    Session session = sessionFactory.openSession();

    public void cancelOrder(Long orderId) {
        Transaction transaction = session.beginTransaction();

        try {
            Order order = session.find(Order.class, orderId);
            if (order == null) {
                throw new Exception("Đơn hàng không tồn tại!");
            }

            order.setStatus("CANCELLED");
            session.merge(order);

            Product product = session.find(Product.class, order.getProductId());
            product.setStock(product.getStock() + order.getQuantity());
            session.merge(product);

            transaction.commit();
            System.out.println("Hủy đơn hàng và hoàn kho thành công!");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Lỗi: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
