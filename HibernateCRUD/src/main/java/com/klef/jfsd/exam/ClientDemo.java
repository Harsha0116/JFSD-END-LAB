package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    public static void main(String[] args) {
   
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Department.class);

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        insertD(sessionFactory, "CSE", "A1", "Dr.Surya");

        deleteD(sessionFactory, 1); 
    }

    public static void insertD(SessionFactory sessionFactory, String name, String location, String hodName) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Department dept = new Department();
            dept.setName(name);
            dept.setLocation(location);
            dept.setHodName(hodName);

            session.persist(dept);

            transaction.commit();
            System.out.println("Department inserted successfully!");
        }
    }

    public static void deleteD(SessionFactory sessionFactory, int departmentId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            String urg = "DELETE FROM Department WHERE departmentId = ?1";
            int res = session.createQuery(urg)
                                .setParameter(1, departmentId).executeUpdate();

            transaction.commit();

          
        }
    }
}
