package server;

public class ServerProxyFactory {
    private static IServerProxy instance;
    
    public static IServerProxy getInstance()
    {
        if(instance == null)
        {
            instance = new DBServerProxy();
        }
        
        return instance;
    }
}
