UNAME_S := $(shell uname -s)

ifeq ($(UNAME_S),Linux)
	ANSWER = $$(xclip -selection clipboard -o)
endif

ifeq ($(UNAME_S),Darwin)
	ANSWER = $$(pbpaste)
endif

download-day-%:
	@rm -f resources/day-$$(printf "%02d" $*).txt > /dev/null
	@aoc d -y 2017 -d $* -f resources/day-$$(printf "%02d" $*).txt
.PHONY: download-day-X

submit-day.part-%:
	@aoc s -y 2017 -d $$(echo $* | cut -d. -f1) $$(echo $* | cut -d. -f2) $(ANSWER)
.PHONY: submit-day.part-X.Y
