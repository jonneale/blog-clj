(ns blog-clj.web
  (:require [compojure.route  :as route]
            [blog.home    :as home])
  (:use [compojure.core :only (defroutes GET)]
        [ring.adapter.jetty]
        [ring.util.response]))

(defroutes app
  (GET "/" [] (home/html))
  (route/resources "/")
  (route/not-found 
   "<h1>Page not found</h1>"))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
