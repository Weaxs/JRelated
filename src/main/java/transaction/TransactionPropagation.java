package transaction;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * spring事物的7中传播机制
 *
 * PROPAGATION_REQUIRED 若当前存在事务，则加入该事务，若不存在事务，则新建一个事务 (默认)
 *                      Propagation propagation() default Propagation.REQUIRED;
 *
 * PAOPAGATION_REQUIRE_NEW  若当前没有事务，则新建一个事务。若当前存在事务，则新建一个事务，新老事务相互独立。外部事务抛出异常回滚不会影响内部事务的正常提交。
 *
 * PROPAGATION_NESTED 如果当前存在事务，则嵌套在当前事务中执行(子事务)。如果当前没有事务，则新建一个事务，类似于REQUIRE_NEW
 *
 * PROPAGATION_SUPPORTS 支持当前事务，若当前不存在事务，以非事务的方式执行
 *
 * PROPAGATION_NOT_SUPPORTED 以非事务的方式执行，若当前存在事务，则把当前事务挂起
 *
 * PROPAGATION_MANDATORY  强制事务执行，若当前不存在事务，则抛出异常
 *
 * PROPAGATION_NEVER    以非事务的方式执行，如果当前存在事务，则抛出异常
 *
 *
 */
public class TransactionPropagation {

    @Transactional
    private void transactionA() {
        TransactionTest test = new TransactionTest();
        test.transactionB();
        test.transactionC();
        test.transactionD();
        test.transactionE();
        test.transactionF();
        test.transactionG();
        test.transactionH();
    }



}

class TransactionTest {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void transactionB() {
        // 启动一个新的事务，transactionA和transactionB不冲突
        // transactionA如果执行失败回滚不会影响transactionB
        // 同理transactionB执行失败回滚不会影响transactionA

        // 问题在于：当我transactionA和transactionB中都有更新时，此时可能会产生死锁
        // transactionA和transactionB对同一个单据相同id进行更新，然后提交会进行锁等待，甚至有可能死锁超时

    }
    @Transactional(propagation = Propagation.NESTED)
    protected void transactionC() {
        // 启动一个新的子事务
        // transactionA如果执行失败回滚transactionC同样回滚
        // 同理transactionC执行失败回滚不会影响transactionA

        // 当transactionA和transactionC都有更新同一单据相同id时，不会产生死锁
        // 因为C仅仅是一个子事务


    }
    @Transactional(propagation = Propagation.SUPPORTS)
    protected void transactionD() {

    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    protected void transactionE() {

    }
    @Transactional(propagation = Propagation.MANDATORY)
    protected void transactionF() {

    }
    @Transactional(propagation = Propagation.NEVER)
    protected void transactionG() {

    }
    @Transactional(propagation = Propagation.REQUIRED)
    protected void transactionH() {

    }
}
