package com.jmp2017w.bean;


import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.LifecycleProcessor;
import org.springframework.context.SmartLifecycle;

/**
 * Bean F.
 */
public class F implements SmartLifecycle
{
    private static Logger logger = Logger.getLogger(F.class);

    private boolean isRunning = false;

    /**
     * Init method.
     */
    public void init()
    {
        logger.info("Initialization of F object");
    }

    /**
     * Destroy method.
     */
    public void destroy()
    {
        logger.info("Destruction of F object");
    }

    /**
     * Start this component.
     * <p>Should not throw an exception if the component is already running.
     * <p>In the case of a container, this will propagate the start signal to all
     * components that apply.
     * @see SmartLifecycle#isAutoStartup()
     */
    public void start()
    {
        logger.info("Starting of F object");
        isRunning = true;
    }

    /**
     * Stop this component, typically in a synchronous fashion, such that the component is
     * fully stopped upon return of this method. Consider implementing {@link SmartLifecycle}
     * and its {@code stop(Runnable)} variant when asynchronous stop behavior is necessary.
     * <p>Note that this stop notification is not guaranteed to come before destruction: On
     * regular shutdown, {@code Lifecycle} beans will first receive a stop notification before
     * the general destruction callbacks are being propagated; however, on hot refresh during a
     * context's lifetime or on aborted refresh attempts, only destroy methods will be called.
     * <p>Should not throw an exception if the component isn't started yet.
     * <p>In the case of a container, this will propagate the stop signal to all components
     * that apply.
     * @see SmartLifecycle#stop(Runnable)
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    public void stop()
    {
        logger.info("Stopping of F object");
        isRunning = false;
    }

    /**
     * Check whether this component is currently running.
     * <p>In the case of a container, this will return {@code true} only if <i>all</i>
     * components that apply are currently running.
     * @return whether the component is currently running
     */
    public boolean isRunning()
    {
        logger.info("F object is running: " + isRunning);
        return isRunning;
    }

    /**
     * Indicates that a Lifecycle component must stop if it is currently running.
     * <p>The provided callback is used by the {@link LifecycleProcessor} to support
     * an ordered, and potentially concurrent, shutdown of all components having a
     * common shutdown order value. The callback <b>must</b> be executed after
     * the {@code SmartLifecycle} component does indeed stop.
     * <p>The {@link LifecycleProcessor} will call <i>only</i> this variant of the
     * {@code stop} method; i.e. {@link Lifecycle#stop()} will not be called for
     * {@code SmartLifecycle} implementations unless explicitly delegated to within
     * the implementation of this method.
     * @see #stop()
     * @see #getPhase()
     */
    public void stop(Runnable runnable)
    {
        stop();
        logger.info("Running callback for object F");
        runnable.run();
    }

    /**
     * Returns {@code true} if this {@code Lifecycle} component should get
     * started automatically by the container at the time that the containing
     * {@link ApplicationContext} gets refreshed.
     * <p>A value of {@code false} indicates that the component is intended to
     * be started through an explicit {@link #start()} call instead, analogous
     * to a plain {@link Lifecycle} implementation.
     * @see #start()
     * @see #getPhase()
     * @see LifecycleProcessor#onRefresh()
     * @see ConfigurableApplicationContext#refresh()
     */
    public boolean isAutoStartup()
    {
        return true;
    }

    /**
     * Return the phase value of this object.
     */
    public int getPhase()
    {
        return Integer.MAX_VALUE;
    }
}