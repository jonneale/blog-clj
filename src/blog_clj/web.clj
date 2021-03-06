(ns blog-clj.web
  (:require [compojure.route  :as route]
            [blog-clj.home    :as home]
            [blog-clj.db      :as db]
            [blog-clj.admin   :as admin])
  (:use [compojure.core :only (defroutes GET)]
        [ring.adapter.jetty]
        [ring.util.response]))

(defroutes app
  (GET "/" []
       (db/maybe-init)
       (db/increment-visits!)
       (home/html (db/visit-count)))
  (GET "/admin" []
       (admin/html))
  (route/resources "/")
  (route/not-found 
   "<h1>Page not found</h1>"))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
