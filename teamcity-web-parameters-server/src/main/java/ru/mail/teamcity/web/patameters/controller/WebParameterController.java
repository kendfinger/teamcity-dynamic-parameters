package ru.mail.teamcity.web.patameters.controller;

import jetbrains.buildServer.controllers.parameters.InvalidParametersException;
import jetbrains.buildServer.controllers.parameters.ParameterRenderContext;
import jetbrains.buildServer.controllers.parameters.api.ParameterControlProviderAdapter;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * User: g.chernyshev
 * Date: 27.06.14
 * Time: 17:51
 */
public class WebParameterController extends ParameterControlProviderAdapter {

    @NotNull
    private final PluginDescriptor pluginDescriptor;

    public WebParameterController(
            @NotNull PluginDescriptor pluginDescriptor
    ) {
        this.pluginDescriptor = pluginDescriptor;
    }

    @NotNull
    @Override
    public String getParameterType() {
        return "webPopulatedSelect";
    }

    @NotNull
    @Override
    public String getParameterDescription() {
        return "Web populated select";
    }

    @NotNull
    @Override
    public ModelAndView renderControl(@NotNull HttpServletRequest request, @NotNull ParameterRenderContext context) throws InvalidParametersException {
        ModelAndView modelAndView = new ModelAndView(pluginDescriptor.getPluginResourcesPath("ru/mail/teamcity/web/parameters/jsp/webParameterControl.jsp"));
        modelAndView.getModel().put("options", requestOptions());
        return modelAndView;
    }

    private List<Entry<String, String>> requestOptions() {
        List<Entry<String, String>> result = new ArrayList<Entry<String, String>>();


        result.add(new Entry<String, String>("first", "first"));
        result.add(new Entry<String, String>("second", "second"));
        result.add(new Entry<String, String>("third", "third"));

        return result;
    }


    public final class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }
}