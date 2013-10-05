(ns app
    (:require [app.world :as world]))

;(js/alert (world/default-string))

(defn drawSquare []
    (let [target (.getElementById js/document "tunelcanvas")
          context (.getContext target "2d")]
       (.fillRect context 0 0 100 100)
    ))

(defn ^:export init []
     (drawSquare)
  )

(defn hack-body [] (set! (.. js/document -body -innerHTML) "<h1>Vidi жџћчћжђжџ ! </h1> <h1>Nézd meg' áűéüóÜ ! </h1>"))
(init)
;(init)
;uncoment line below and command/control enter it to evaluate it

;(hack-body)
