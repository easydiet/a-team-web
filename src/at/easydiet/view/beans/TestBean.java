package at.easydiet.view.beans;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;

@ManagedBean
@SessionScoped
public class TestBean
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(TestBean.class);
    
    private ClientIdHashMap _text;
    
    public String getText(UIComponent component)
    {
        return component.getClientId();
    }
    
    private static class ClientIdHashMap implements Map<UIComponent, String>
    {
        private HashMap<UIComponent, String> _map = new HashMap<UIComponent, String>();

        /**
         * @return
         * @see java.util.HashMap#size()
         */
        public int size()
        {
            return _map.size();
        }

        /**
         * @return
         * @see java.util.HashMap#isEmpty()
         */
        public boolean isEmpty()
        {
            return _map.isEmpty();
        }

        /**
         * @param key
         * @return
         * @see java.util.HashMap#get(java.lang.Object)
         */
        public String get(UIComponent key)
        {
            if(key == null) return "{null}";
            if(!containsKey(key))
            {
                put(key, key.getClientId());
            }
            return _map.get(key);
        }

        /**
         * @param o
         * @return
         * @see java.util.AbstractMap#equals(java.lang.Object)
         */
        public boolean equals(Object o)
        {
            return _map.equals(o);
        }

        /**
         * @param key
         * @return
         * @see java.util.HashMap#containsKey(java.lang.Object)
         */
        public boolean containsKey(Object key)
        {
            return _map.containsKey(key);
        }

        /**
         * @param key
         * @param value
         * @return
         * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
         */
        public String put(UIComponent key, String value)
        {
            return _map.put(key, value);
        }

        /**
         * @return
         * @see java.util.AbstractMap#hashCode()
         */
        public int hashCode()
        {
            return _map.hashCode();
        }

        /**
         * @return
         * @see java.util.AbstractMap#toString()
         */
        public String toString()
        {
            return _map.toString();
        }

        /**
         * @param m
         * @see java.util.HashMap#putAll(java.util.Map)
         */
        public void putAll(Map<? extends UIComponent, ? extends String> m)
        {
            _map.putAll(m);
        }

        /**
         * @param key
         * @return
         * @see java.util.HashMap#remove(java.lang.Object)
         */
        public String remove(Object key)
        {
            return _map.remove(key);
        }

        /**
         * 
         * @see java.util.HashMap#clear()
         */
        public void clear()
        {
            _map.clear();
        }

        /**
         * @param value
         * @return
         * @see java.util.HashMap#containsValue(java.lang.Object)
         */
        public boolean containsValue(Object value)
        {
            return _map.containsValue(value);
        }

        /**
         * @return
         * @see java.util.HashMap#clone()
         */
        public Object clone()
        {
            return _map.clone();
        }

        /**
         * @return
         * @see java.util.HashMap#keySet()
         */
        public Set<UIComponent> keySet()
        {
            return _map.keySet();
        }

        /**
         * @return
         * @see java.util.HashMap#values()
         */
        public Collection<String> values()
        {
            return _map.values();
        }

        /**
         * @return
         * @see java.util.HashMap#entrySet()
         */
        public Set<java.util.Map.Entry<UIComponent, String>> entrySet()
        {
            return _map.entrySet();
        }

        @Override
        public String get(Object key)
        {
            if(key instanceof UIComponent) return get((UIComponent)key);
            return "{no ui}";
        }

        
        
        
    }
}
