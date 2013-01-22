(ns blog-clj.web
  (:use [compojure.core :only (defroutes GET)]
        [ring.adapter.jetty]
        [ring.util.response]))

(defroutes app
  (GET "/" [] "<h2>Hello World</h2>"))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
