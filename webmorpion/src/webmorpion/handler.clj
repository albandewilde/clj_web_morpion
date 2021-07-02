(ns webmorpion.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [selmer.parser :as parser]))

(defn ttt []
  (parser/render-file "./templates/home.html" {
                                               :c00 0, :c01 0, :c02 0,
                                               :c10 0, :c11 0, :c12 0,
                                               :c20 0, :c21 0, :c22 0,
                                               :board "c00=0&c01=0&c02=0&c10=0&c11=0&c12=0&c20=0&c21=0&c22=0&player=1"
                                               }))

(defn play [params]
  ;; Get the player from the query string
  (def box (get params "box"))
  (def player (Integer/parseInt (get params "player")))

  ;; Get the board from the query string
  (def board {
              "c00" (Integer/parseInt (get params "c00")), "c01" (Integer/parseInt (get params "c01")), "c02" (Integer/parseInt (get params "c02")),
              "c10" (Integer/parseInt (get params "c10")), "c11" (Integer/parseInt (get params "c11")), "c12" (Integer/parseInt (get params "c12")),
              "c20" (Integer/parseInt (get params "c20")), "c21" (Integer/parseInt (get params "c21")), "c22" (Integer/parseInt (get params "c22")),
              })

  (def board (assoc board box player))
  (def queryBoard (clojure.string/join [
                                        "c00=" (get board "c00") "&"
                                        "c01=" (get board "c01") "&"
                                        "c02=" (get board "c02") "&"
                                        "c10=" (get board "c10") "&"
                                        "c11=" (get board "c11") "&"
                                        "c12=" (get board "c12") "&"
                                        "c20=" (get board "c20") "&"
                                        "c21=" (get board "c21") "&"
                                        "c22=" (get board "c22") "&"
                                        "player=" (if (== player 1) 2 1)]))

  ;; Display the modified grid
  (parser/render-file "./templates/home.html" {
                                               :c00 (get board "c00"), :c01 (get board "c01"), :c02 (get board "c02"),
                                               :c10 (get board "c10"), :c11 (get board "c11"), :c12 (get board "c12"),
                                               :c20 (get board "c20"), :c21 (get board "c21"), :c22 (get board "c22"),
                                               :board queryBoard}))

(defn endgame [player]
  ;; Get the winner from the query string
  (def p (Integer/parseInt player))

  ;; Display a page to congrat him
  (parser/render-file "./templates/win.html" {:p p}))


(defroutes app-routes
  (GET "/" [] (ttt))
  (GET "/play" {params :query-params} (play params))
  (GET "/endgame" {params :query-params} (endgame (get params "p")))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
